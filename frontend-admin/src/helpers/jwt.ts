import cookie from './cookie';

const jwt = () => {
    window.addEventListener('storage', (event) => {
        if (event.key === 'ra-logout') {
            cookie.remove('access-token');
        }
    });

    function getToken() {
        return cookie.get('access-token');
    }

    function setToken(jwt: string) {
        cookie.set('access-token', jwt);
    }

    function removeToken() {
        cookie.remove('access-token');
    }

    return {
        getToken,
        setToken,
        removeToken
    };
};

export default jwt();
