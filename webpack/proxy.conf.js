function setupProxy() {
  const tls = process.env.TLS;
  const conf = [
    {
      context: [
        '/api',
      ],
      target: `http${tls ? 's' : ''}://localhost:8789`,
      secure: false,
      changeOrigin: tls,
    },
  ];
  return conf;
}

module.exports = setupProxy();
