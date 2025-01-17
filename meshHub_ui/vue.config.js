const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {

    proxy: {
      "/static": {
        target: "http://localhost:9999/meshHub", // 需要代理的域名
        // ws: false, // 是否启用websockets
        changeOrigin: true, //开启代理：在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
        // pathRewrite: {  //重写匹配的字段，如果不需要在请求路径上，重写为""
        //   "^/api": ""
        // }
      },
      "/api": {
        target: "http://localhost:8081/",
        changeOrigin: true,
      },

    },


  }
})
