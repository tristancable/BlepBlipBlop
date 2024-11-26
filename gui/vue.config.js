module.exports = {
  transpileDependencies: ['vuetify'],
  devServer: {
    port: 8081,
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Replace with Spring Boot port
        changeOrigin: true,
      },
    },
  },

  pluginOptions: {
    vuetify: {
      // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    },
  },

  configureWebpack: {
    plugins: [
      new (require('webpack')).DefinePlugin({
        // Define Vue feature flags
        __VUE_OPTIONS_API__: true, // Enable Options API (if you're using it)
        __VUE_PROD_DEVTOOLS__: false, // Disable devtools in production
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: false, // Disable hydration mismatch logs
      }),
    ],
  },
};