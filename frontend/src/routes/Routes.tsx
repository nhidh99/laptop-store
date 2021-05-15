import MainLayout from '@/layouts/MainLayout';
import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import HomeRoute from './HomeRoute';

export default function Routes() {
    return (
        <BrowserRouter>
            <MainLayout>
                <HomeRoute />
            </MainLayout>
        </BrowserRouter>
    );
}
