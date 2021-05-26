import axios, { AxiosResponse } from 'axios';
import cookie from './cookie';
import jwt from './jwt';

function setAccessAndRefreshToken(response: AxiosResponse<any>) {
    const accessToken = response.headers['x-access-token'];
    const refreshToken = response.headers['x-refresh-token'];
    jwt.setToken(accessToken);
    cookie.set('refresh-token', refreshToken);
}

const auth = {
    login: async function (username: string, password: string) {
        const config = { headers: { 'Content-Type': 'application/json' } };
        const data = { username: username, password: password };
        const response = await axios.post('/api/auth/login', data, config);
        setAccessAndRefreshToken(response);
    },

    refreshToken: async function () {
        const config = {
            headers: {
                'Content-Type': 'application/json',
                'x-access-token': jwt.getToken(),
                'x-refresh-token': cookie.get('refresh-token')
            }
        };
        const response = await axios.post('/api/auth/token', null, config);
        setAccessAndRefreshToken(response);
    }
};

export default auth;
