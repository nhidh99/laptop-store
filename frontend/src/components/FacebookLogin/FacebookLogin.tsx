import ReactFacebookLogin from 'react-facebook-login';
import React from 'react';
import authAPI from '@/apis/authAPI';

export default function FacebookLogin() {
    async function handleResponse(fbResponse) {
        try {
            await authAPI.postLoginByFacebook(fbResponse);
            alert('OK');
        } catch (err) {
            alert('Failed!');
        }
    }

    return (
        <ReactFacebookLogin
            appId="523568205328673"
            fields="name,email,picture"
            autoLoad={false}
            callback={handleResponse}
        />
    );
}
