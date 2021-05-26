import auth from '@/helpers/auth';
import cookie from '@/helpers/cookie';
import jwt from '@/helpers/jwt';

const authProvider = {
    login: async ({ username, password }) => {
        try {
            await auth.login(username, password);
            return Promise.resolve();
        } catch (err) {
            return Promise.reject();
        }
    },

    logout: () => {
        jwt.removeToken();
        cookie.remove('refresh-token');
        return Promise.resolve();
    },

    checkError: ({ status }) => {
        if (status === 401) {
            jwt.removeToken();
            cookie.remove('refresh-token');
            return Promise.reject();
        }
        return Promise.resolve();
    },

    checkAuth: () => {
        return jwt.getToken() ? Promise.resolve() : Promise.reject();
    },

    getPermissions: () => Promise.resolve()
};

export default authProvider;
