import { PostCreate } from '@/entities/Post/PostCreate';
import { PostEdit } from '@/entities/Post/PostEdit';
import { PostList } from '@/entities/Post/PostList';
import { UserList } from '@/entities/User/UserList';
import jwt from '@/helpers/jwt';
import Dashboard from '@/pages/Dashboard/Dashboard';
import authProvider from '@/providers/authProvider';
import dataProvider from '@/providers/restProvider';
import PostIcon from '@material-ui/icons/Book';
import UserIcon from '@material-ui/icons/Group';
import React from 'react';
import { Admin, EditGuesser, fetchUtils, Resource } from 'react-admin';

const httpClient = (url, options = { headers: null }) => {
    if (!options.headers) {
        options.headers = new Headers({ Accept: 'application/json' });
    }
    const token = jwt.getToken();
    options.headers.set('Authorization', `Bearer ${token}`);
    return fetchUtils.fetchJson(url, options);
};

const myDataProvider = dataProvider('/api', httpClient);

function App() {
    return (
        <Admin
            //@ts-ignore
            dataProvider={myDataProvider}
            authProvider={authProvider}
            dashboard={Dashboard}
        >
            <Resource name="users" list={UserList} edit={EditGuesser} icon={UserIcon} />
            <Resource
                name="posts"
                list={PostList}
                edit={PostEdit}
                create={PostCreate}
                icon={PostIcon}
            />
        </Admin>
    );
}

export default App;
