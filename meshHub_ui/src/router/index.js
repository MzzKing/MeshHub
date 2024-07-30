import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  // {
  //   path: '/vue',
  //   name:'vue',
  //   component: Vue,
  // },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/mall',
    name:'mall',
    component: ()=>import('../views/MallModel.vue')
  },
  {
    path:'/register',
    name:'register',
    component: ()=>import('../views/RegisterView.vue')
  },
  {
    path:'/OneModelView',
    name:'OneModelView',
    component: ()=>import('../views/OneModelView.vue')
  },
  {
    path:'/model',
    name:'model',
    component: ()=>import('../views/ModelView.vue')
  },
  {
    path:'/userInfo',
    name:'userInfo',
    component: ()=>import('../views/UserInfoView.vue')
  },
  {
    path:'/login',
    name:'login',
    component: ()=>import('../views/LoginView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

let originPush = VueRouter.prototype.push
let originReplace = VueRouter.prototype.replace
VueRouter.prototype.push = function (location, res, rej) {
  if (res && rej) {
    originPush.call(this, location, res, rej)
  }
  else {
    originPush.call(this, location, () => { }, () => { })
  }

}
//重写replace方法
VueRouter.prototype.replace = function (location, res, rej) {
  if (res && rej) {
    originReplace.call(this, location, res, rej)
  }
  else {
    originReplace.call(this, location, () => { }, () => { })
  }
}

export default router
