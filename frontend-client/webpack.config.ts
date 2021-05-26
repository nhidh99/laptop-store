import path from 'path';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import ForkTsCheckerWebpackPlugin from 'fork-ts-checker-webpack-plugin';
import TsconfigPathsPlugin from 'tsconfig-paths-webpack-plugin';
import { Configuration as WebpackConfiguration, DefinePlugin } from 'webpack';
import { Configuration as WebpackDevServerConfiguration } from 'webpack-dev-server';
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

interface Configuration extends WebpackConfiguration {
    devServer?: WebpackDevServerConfiguration;
}

const webpackConfig = (): Configuration => ({
    entry: './src/index.tsx',
    ...(process.env.production || !process.env.development ? {} : { devtool: 'eval-source-map' }),

    resolve: {
        extensions: ['.ts', '.tsx', '.js'],
        plugins: [new TsconfigPathsPlugin({ configFile: './tsconfig.json' })]
    },
    output: {
        path: path.join(__dirname, '/build'),
        filename: 'build.js'
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                loader: 'ts-loader',
                options: {
                    transpileOnly: true
                },
                exclude: /build/
            },
            {
                test: /\.s?css$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                            sourceMap: true
                        }
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            sourceMap: true
                        }
                    },
                    {
                        loader: 'sass-loader',
                        options: {
                            sourceMap: true
                        }
                    }
                ]
            }
        ]
    },
    devServer: {
        port: 3000,
        open: true,
        historyApiFallback: true,
        proxy: {
            '/api': {
                target: 'http://localhost:8080',
                secure: false,
                changeOrigin: true
            }
        }
    },
    plugins: [
        new HtmlWebpackPlugin({
            // HtmlWebpackPlugin simplifies creation of HTML files to serve your webpack bundles
            template: './public/index.html'
        }),
        // DefinePlugin allows you to create global constants which can be configured at compile time
        new DefinePlugin({
            'process.env': process.env.production || !process.env.development
        }),
        new ForkTsCheckerWebpackPlugin({
            // Speeds up TypeScript type checking and ESLint linting (by moving each to a separate process)
            eslint: {
                files: './src/**/*.{ts,tsx,js,jsx}'
            }
        })
    ]
});

export default webpackConfig;
