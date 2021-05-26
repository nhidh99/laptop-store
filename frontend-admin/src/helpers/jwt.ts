import axios from 'axios';
import cookie from './cookie';

const jwt = () => {
    let inMemoryJwt = null;
    const refreshEndpoint = '/auth/refresh-token';
    const logoutEventName = 'ra-logout';

    window.addEventListener('storage', (event) => {
        if (event.key === logoutEventName) {
            inMemoryJwt = null;
        }
    });

    async function refreshToken() {
        const config = {
            headers: {
                'Content-Type': 'application/json',
                'x-refresh-token': cookie.get('refresh-token')
            }
        };
        try {
            const response = await axios.post(refreshEndpoint, null, config);
            const accessToken = response.headers['x-access-token'];
            const refreshToken = response.headers['x-refresh-token'];
            setToken(accessToken);
            cookie.set('refresh-token', refreshToken);
            return true;
        } catch (err) {
            return false;
        }
    }

    function getToken() {
        return inMemoryJwt;
    }

    function setToken(jwt: string) {
        inMemoryJwt = jwt;
    }

    function removeToken() {
        inMemoryJwt = null;
    }

    return {
        refreshToken,
        getToken,
        setToken,
        removeToken
    };
};

export default jwt();
