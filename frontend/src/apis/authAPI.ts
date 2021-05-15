import publicAxios from '@/axios/publicAxios';

const authAPI = {
    postLoginByFacebook: function (fbResponse) {
        const config = { headers: { 'x-facebook-token': fbResponse.accessToken } };
        return publicAxios.post('/auth/login/facebook', fbResponse, config);
    }
};

export default authAPI;
