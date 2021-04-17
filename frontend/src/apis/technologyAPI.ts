import axios from 'axios';

const url = 'https://6072cf1ce4e0160017ddeeca.mockapi.io/api';

const technologyAPI = {
    getAll: function () {
        return axios.get(`${url}/technologies`);
    }
};

export default technologyAPI;
