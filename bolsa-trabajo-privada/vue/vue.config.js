const VuetifyLoaderPlugin = require('vuetify-loader/lib/plugin');

const globalSassFiles = [
  './src/styles/utils/_variables.scss',
  './src/styles/utils/_mixins.scss',
];

module.exports = {
  outputDir: process.env.NODE_ENV === 'production' ? 'dist/bolsa-de-trabajo-admin' : 'dist',
  publicPath: process.env.NODE_ENV === 'production' ? '/bolsa-de-trabajo-admin/' : '/',
  assetsDir: process.env.NODE_ENV === 'production' ? 'public/' : '',
  chainWebpack: (config) => {
    config
      .plugin('html')
      .tap((args) => {
        if (args[0].minify) args[0].minify.removeAttributeQuotes = false;

        return args;
      });

    const oneOfsMap = config.module.rule('scss').oneOfs.store;
    oneOfsMap.forEach((item) => {
      item
        .use('sass-resources-loader')
        .loader('sass-resources-loader')
        .options({
          resources: globalSassFiles,
        })
        .end();
    });
  },
};
