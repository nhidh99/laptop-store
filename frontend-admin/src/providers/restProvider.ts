/* eslint-disable no-console */
import {
    CREATE,
    DELETE,
    DELETE_MANY,
    GET_LIST,
    GET_MANY,
    GET_MANY_REFERENCE,
    GET_ONE,
    UPDATE,
    UPDATE_MANY
} from 'react-admin';

const dataProvider = (apiUrl, httpClient) => {
    /**
     * @param {String} type One of the constants appearing at the top if this file, e.g. 'UPDATE'
     * @param {String} resource Name of the resource to fetch, e.g. 'posts'
     * @param {Object} params The data request params, depending on the type
     * @returns {Object} { url, options } The HTTP request parameters
     */
    const convertDataRequestToHTTP = (type, resource, params) => {
        let url = '';
        const options = { method: '', body: null };
        switch (type) {
            case GET_LIST: {
                const { page, perPage } = params.pagination;
                options.method = 'GET';
                url = `${apiUrl}/${resource}?page=${page}&pageSize=${perPage}`;
                break;
            }
            case GET_ONE:
                url = `${apiUrl}/${resource}/${params.id}`;
                break;
            case GET_MANY: {
                // const query = {
                //     filter: JSON.stringify({ id: params.ids })
                // };
                let idStr = '';
                // const queryString = params.ids.map((id) => idStr + `id=${id}`);
                url = `${apiUrl}/${resource}?${idStr}}`;
                break;
            }
            case GET_MANY_REFERENCE: {
                const { page, perPage } = params.pagination;
                url = `${apiUrl}/${resource}?page=${page}&pageSize=${perPage}`;
                break;
            }
            case UPDATE:
                url = `${apiUrl}/${resource}/${params.id}`;
                options.method = 'PUT';
                options.body = JSON.stringify(params.data);
                break;
            case CREATE:
                url = `${apiUrl}/${resource}`;
                options.method = 'POST';
                options.body = JSON.stringify(params.data);
                break;
            case DELETE:
                url = `${apiUrl}/${resource}/${params.id}`;
                options.method = 'DELETE';
                break;
            default:
                throw new Error(`Unsupported fetch action type ${type}`);
        }
        return { url, options };
    };

    /**
     * @param {Object} response HTTP response from fetch()
     * @param {String} type One of the constants appearing at the top if this file, e.g. 'UPDATE'
     * @param {String} resource Name of the resource to fetch, e.g. 'posts'
     * @param {Object} params The data request params, depending on the type
     * @returns {Object} Data response
     */
    const convertHTTPResponse = (response, type, resource, params) => {
        const { headers, json } = response;
        switch (type) {
            case GET_LIST:
            case GET_MANY_REFERENCE:
                if (!headers.has('x-total-count')) {
                    throw new Error(
                        'The numberOfElements property must be must be present in the Json response'
                    );
                }
                return {
                    data: json,
                    total: parseInt(headers.get('x-total-count'))
                };
            case CREATE:
                return { data: { ...params.data, id: json.id } };
            default:
                return { data: json };
        }
    };

    /**
     * @param {string} type Request type, e.g GET_LIST
     * @param {string} resource Resource name, e.g. "posts"
     * @param {Object} payload Request parameters. Depends on the request type
     * @returns {Promise} the Promise for a data response
     */
    return (type, resource, params) => {
        // simple-rest doesn't handle filters on UPDATE route, so we fallback to calling UPDATE n times instead
        if (type === UPDATE_MANY) {
            return Promise.all(
                params.ids.map((id) =>
                    httpClient(`${apiUrl}/${resource}/${id}`, {
                        method: 'PUT',
                        body: JSON.stringify(params.data)
                    })
                )
            ).then((responses) => ({
                // @ts-ignore
                data: responses.map((response) => response.json)
            }));
        }
        // simple-rest doesn't handle filters on DELETE route, so we fallback to calling DELETE n times instead
        if (type === DELETE_MANY) {
            return Promise.all(
                params.ids.map((id) =>
                    httpClient(`${apiUrl}/${resource}/${id}`, {
                        method: 'DELETE'
                    })
                )
            ).then((responses) => ({
                // @ts-ignore
                data: responses.map((response) => response.json)
            }));
        }

        const { url, options } = convertDataRequestToHTTP(type, resource, params);
        return httpClient(url, options).then((response) => {
            console.log(response);
            return convertHTTPResponse(response, type, resource, params);
        });
    };
};

export default dataProvider;
