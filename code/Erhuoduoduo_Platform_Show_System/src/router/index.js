import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

//路由信息数组
const routes = [
	{
		path:'/',
		component:()=>import('@v/Login.vue'),
		redirect: '/login'
	},
    {//登录页
		path:'/login',
		component:()=>import('@v/Login.vue')
	},
	{//注册页
		path:'/register',
		component:()=>import('@v/Register.vue')
	},
	{//用户主界面
		path:'/user/customHome',
		name:'customHome',
		component:()=>import('@v/CustomHome')
	},
	{//用户主界面
		path:'/mainPage',
		name:'mainPage',
		component:()=>import('@v/MainPage')
	}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
