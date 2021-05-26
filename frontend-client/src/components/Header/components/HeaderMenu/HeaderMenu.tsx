import { GiHamburgerMenu } from 'react-icons/gi';
import styles from './HeaderMenu.module.scss';
import React from 'react';

export default function HeaderMenu() {
    return (
        <div className={styles.menu}>
            <GiHamburgerMenu className={styles.icon} />
        </div>
    );
}
