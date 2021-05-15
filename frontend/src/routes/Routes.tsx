import MainLayout from '@/layouts/MainLayout';
import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import HomeRoute from './HomeRoute/HomeRoute';

export default function Routes() {
    return (
        <MainLayout>
            <BrowserRouter>
                <HomeRoute />
            </BrowserRouter>
        </MainLayout>
    );
}
