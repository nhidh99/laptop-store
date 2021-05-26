import { PostEdit } from '@/entities/Post/PostEdit';
import { PostList } from '@/entities/Post/PostList';
import { PostCreate } from '@/entities/Post/PostCreate';
import { UserList } from '@/entities/User/UserList';
import UserIcon from '@material-ui/icons/Group';
import PostIcon from '@material-ui/icons/Book';
import jsonServerProvider from 'ra-data-json-server';
import React from 'react';
import { Admin, EditGuesser, Resource } from 'react-admin';
import Dashboard from '@/pages/Dashboard/Dashboard';
import authProvider from '@/providers/authProvider';

const dataProvider = jsonServerProvider('https://jsonplaceholder.typicode.com');

function App() {
    return (
        <Admin dataProvider={dataProvider} authProvider={authProvider} dashboard={Dashboard}>
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
