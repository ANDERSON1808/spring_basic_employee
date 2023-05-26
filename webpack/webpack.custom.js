const webpack = require('webpack');
const { merge } = require('webpack-merge');
const path = require('path');
const BrowserSyncPlugin = require('browser-sync-webpack-plugin');
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const WebpackNotifierPlugin = require('webpack-notifier');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const ESLintPlugin = require('eslint-webpack-plugin');

const environment = require('./environment');

const tls = process.env.TLS;

module.exports = (config, options, targetOptions) => {
  config.cache = {
    // 1. Set cache type to filesystem
    type: 'filesystem',
    cacheDirectory: path.resolve(__dirname, '../target/webpack'),
    buildDependencies: {
      // 2. Add your config as buildDependency to get cache invalidation on config change
      config: [
        __filename,
        path.resolve(__dirname, 'webpack.custom.js'),
        path.resolve(__dirname, '../angular.json'),
        path.resolve(__dirname, '../tsconfig.app.json'),
        path.resolve(__dirname, '../tsconfig.json'),
      ],
    },
  };

  // PLUGINS
  if (config.mode === 'development') {
    config.plugins.push(
      new ESLintPlugin({
        extensions: ['js', 'ts'],
      }),
      new WebpackNotifierPlugin({
        title: 'Employes',
        contentImage: path.join(__dirname, 'logo.png'),
      })
    );
  }
  if (targetOptions.target === 'serve' || config.watch) {
    config.plugins.push(
      new BrowserSyncPlugin(
        {
          host: 'localhost',
          port: 9000,
          https: tls,
          proxy: {
            target: `http${tls ? 's' : ''}://localhost:${targetOptions.target === 'serve' ? '4200' : '8789'}`,
            proxyOptions: {
              changeOrigin: false,
            },
          },
          socket: {
            clients: {
              heartbeatTimeout: 60000,
            },
          },
        },
        {
          reload: targetOptions.target === 'build', // enabled for build --watch
        }
      )
    );
  }

  if (config.mode === 'production') {
    config.plugins.push(
      new BundleAnalyzerPlugin({
        analyzerMode: 'static',
        openAnalyzer: false,
        // Webpack statistics in target folder
        reportFilename: '../stats.html',
      })
    );
  }

  const patterns = [
  ];

  if (patterns.length > 0) {
    config.plugins.push(new CopyWebpackPlugin({ patterns }));
  }

  config.plugins.push(
    new webpack.DefinePlugin({
      __TIMESTAMP__: JSON.stringify(environment.__TIMESTAMP__),
      // APP_VERSION is passed as an environment variable from the Gradle / Maven build tasks.
      __VERSION__: JSON.stringify(environment.__VERSION__),
      __DEBUG_INFO_ENABLED__: environment.__DEBUG_INFO_ENABLED__ || config.mode === 'development',
      __SERVER_API_URL__: JSON.stringify(environment.__SERVER_API_URL__),
    })
  );

  config = merge(
    config
  );

  return config;
};
