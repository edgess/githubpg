const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
module.exports = {
    entry: {index: './index.js', login: './login.js', oil: './oil.js'},
    output: {filename: '[name][hash:6].bundle.js', path: __dirname + '/dist'},
    module: {
        rules: [
            {test: /\.(scss|css|sass)$/, use: ['style-loader', 'css-loader', 'sass-loader']},
            // {test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/, use: 'url-loader?limit=8192'},
            // {test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/, use: 'file-loader'},
            {test: /\.(woff2|woff|ttf|eot|svg|)$/, use: 'url-loader'},
            // /i 表示不区分大小写
            //不改名 &name=[name].[ext]
            {test: /\.(png|jpg|gif)$/i, use: 'url-loader?limit=8192'},
        ]
    },
    resolve:{
        alias:{
            "vue$":"vue/dist/vue.esm.js"
        }
    },
    plugins: [
        //自动加载模块，而不必到处 import 或 require
        //在此定义打包bootstrap需要用jq和vue
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "window.jQuery": "jquery"
            // Vue: ['vue/dist/vue.esm.js', 'default']
        }),
        //输出模板
        new HtmlWebpackPlugin({
            chunks: ['index'], //添加引入的js,也就是entry中的key
            // hash:true, //向html引入的src链接后面增加一段hash值,消除缓存
            // minify:{
            //     collapseWhitespace:true //折叠空白区域 也就是压缩代码
            // },
            title: 'index page',
            filename: 'index.html',
            template: 'index.html' //模板地址
        }),
        new HtmlWebpackPlugin({
            chunks: ['login'], //添加引入的js,也就是entry中的key
            // hash:true, //向html引入的src链接后面增加一段hash值,消除缓存
            // minify:{
            //     collapseWhitespace:true //折叠空白区域 也就是压缩代码
            // },
            title: 'login page',
            filename: 'login.html',
            template: 'login.html' //模板地址
        }),
        new HtmlWebpackPlugin({
            chunks: ['oil'], //添加引入的js,也就是entry中的key
            // hash:true, //向html引入的src链接后面增加一段hash值,消除缓存
            // minify:{
            //     collapseWhitespace:true //折叠空白区域 也就是压缩代码
            // },
            // title: 'oil page',
            filename: 'oil.html',
            //使用模板加载器（jsp，thymeleaf ），生成模板
            //npm install raw-loader --save-dev
            template: 'raw-loader!./oil.html' //模板地址
        }),
        //打包前清理dist目录
        new CleanWebpackPlugin(),
    ]
};