import React from 'react';
import { Route, Switch } from 'react-router-dom';
import HeaderLogo from './components/HeaderLogo/HeaderLogo';
import HeaderMenu from './components/HeaderMenu/HeaderMenu';
import styles from './Header.module.scss';

export default function Header() {
    return (
        <header className={styles.header}>
            <HeaderLogo />
            <Switch>
                <Route path={''} component={HeaderMenu} />
            </Switch>
        </header>
    );
}
