const path = require("path");
module.exports = {
    entry: {

        list: './src/list.jsx',
        add: './src/add.jsx',
        // detail: './src/detail.jsx'
    },
    devtool: "source-map",
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                loader: 'babel'
            }
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx', ".html"]
    },
    output: {
        path: path.resolve('./dist'),
        publicPath: path.resolve('/'),
        filename: '[name].js'
    },
    devServer: {
        contentBase: path.resolve('./'),
        hot: true,
        // inline: true,
        port: 3005,
        historyApiFallback: true
    }
};
