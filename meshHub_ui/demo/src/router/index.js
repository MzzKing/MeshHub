import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '@/views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name:'home',
    component: ()=>import('@/views/HomeView.vue')
  },
  {
    path:'/mall',
    name:'mall',
    component: ()=>import('@/views/MallView.vue')
  },
  {
    path:'/model',
    name:'model',
    component: ()=>import('@/views/ModelView.vue')
  },
  {
    path:'/userInfo',
    name:'userInfo',
    component: ()=>import('@/views/UserInfoView.vue')
  },
  {
    path:'/login',
    name:'login',
    component: ()=>import('@/views/LoginView.vue')
  },
  {
    path:'/register',
    name:'register',
    component: ()=>import('@/views/RegisterView.vue')
  },
  {
    path:'/search',
    name:'search',
    component: ()=>import('@/views/SearchView.vue')
  },
  {
    path:'/user',
    name:'user',
    component: ()=>import('@/views/UserTest.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
//挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to 将要访问的路径
  // from 代表从哪个路径跳转而来
  // next 是一个函数，表示放行
  //     next()  放行    next('/login')  强制跳转

  if (to.path === '/login') return next()
  // 其他页面则要有token才能放行 之后的网络请求中要拿这个token放入请求头中
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login') /* 没有用else if */
  next()
})
export default router
