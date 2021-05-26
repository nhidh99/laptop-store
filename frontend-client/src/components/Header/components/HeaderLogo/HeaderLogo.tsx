import { GiWolfHowl } from 'react-icons/gi';
import { Link } from 'react-router-dom';
import styles from './HeaderLogo.module.scss';
import React from 'react';

export default function HeaderLogo() {
    return (
        <Link to="/" className={styles.logo}>
            {<GiWolfHowl className={styles.icon} />} Werewolf Boardgame Wiki
        </Link>
    );
}
