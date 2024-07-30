import router from './router'
import store from './store'
import Vue from 'vue';
import {Button, Form, FormItem, Input,Message,Container,Header,Aside,Main,Menu,Submenu,MenuItemGroup,MenuItem} from "element-ui";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import jquery from 'jquery';
import axios from 'axios';

//配置请求根路径
axios.defaults.baseURL='http://127.0.0.1:4523/m1/2525284-0-default/';
axios.interceptors.request.use(config=>{
  console.log(config)
  config.headers.Authorization=window.sessionStorage.getItem('token')
  //最后必须return config
  return config
})
Vue.prototype.$http=axios
Vue.use(ElementUI)
Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItemGroup)
Vue.use(MenuItem)
Vue.prototype.$message=Message;
Vue.config.productionTip = false;

new Vue({
  router,
  jquery,
  store,
  render: h => h(App)
}).$mount('#app')
