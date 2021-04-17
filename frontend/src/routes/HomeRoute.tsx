import { PATH } from '@/constants/paths';
import Home from '@/pages/Home';
import React from 'react';
import { Route, Switch } from 'react-router';

export default function HomeRoute() {
    return (
        <Switch>
            <Route path={PATH.HOME} component={() => <Home />} />
        </Switch>
    );
}
