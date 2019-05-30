var webpack = require('webpack');
module.exports = {
    entry: {index: './index.js'},
    output: {filename: '[name].bundle.js', path: __dirname + '/dist'},
    module: {
        rules: [
            {test: /\.(sc|c|sa)ss$/, use: ['style-loader', 'css-loader', 'sass-loader']},
            {test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/, use: 'url-loader?limit=8192'},
            {test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/, use: 'file-loader'},
            // {test: /\.(png|jpg|gif)$/i, use: 'url-loader?limit=8192'},
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "window.jQuery": "jquery"
        })
    ],
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        }
    }
};