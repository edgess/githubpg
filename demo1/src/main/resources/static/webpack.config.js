const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
module.exports = {
    entry: {index: './index.js'},
    output: {filename: '[name][hash:6].bundle.js', path: __dirname + '/dist'},
    module: {
        rules: [
            {test: /\.(sc|c|sa)ss$/, use: ['style-loader', 'css-loader', 'sass-loader']},
            {test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/, use: 'url-loader?limit=8192'},
            {test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/, use: 'file-loader'},
            // {test: /\.(png|jpg|gif)$/i, use: 'url-loader?limit=8192'},
        ]
    },
    plugins: [
        //自动加载模块，而不必到处 import 或 require
        //在此定义打包bootstrap需要用jq和vue
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "window.jQuery": "jquery",
            Vue: ['vue/dist/vue.esm.js', 'default']
        }),
        //输出模板
        new HtmlWebpackPlugin({
            title: 'index page',
            filename: 'index.html',
            template: 'index.html'
        }),
        //打包前清理dist目录
        new CleanWebpackPlugin(),
    ]
};