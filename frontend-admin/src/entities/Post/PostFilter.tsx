import React from 'react';
import { Filter, ReferenceInput, SelectInput } from 'react-admin';

export const PostFilter = (props) => (
    <Filter {...props}>
        <ReferenceInput label="User" source="userId" reference="users" allowEmpty>
            <SelectInput optionText="name" />
        </ReferenceInput>
    </Filter>
);
