const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "intervlgo-ourfolio.azurewebsites.net",
      changeOrigin: true,
    })
  );
};