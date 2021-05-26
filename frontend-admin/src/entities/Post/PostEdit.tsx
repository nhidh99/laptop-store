import * as React from 'react';
import { Edit, SimpleForm, ReferenceInput, SelectInput, TextInput } from 'react-admin';

function PostTitle({ record }) {
    return <span>Post {record?.title}</span>;
}

export const PostEdit = (props) => (
    <Edit {...props} title={<PostTitle record={props.record} />}>
        <SimpleForm>
            <TextInput source="id" />
            <ReferenceInput source="userId" reference="users">
                <SelectInput optionText="name" />
            </ReferenceInput>
            <TextInput source="title" />
            <TextInput multiline source="body" />
        </SimpleForm>
    </Edit>
);
