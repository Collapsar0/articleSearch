import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from './axios'
import JsonExcel from 'vue-json-excel'
import Blob from './vendor/Blob'
import Export2Excel from './vendor/Export2Excel.js'

//导入插件
import './plugin'
import global_ from './Global.vue'

Vue.component('downloadExcel', JsonExcel)
Vue.prototype.GLOBAL = global_;

Vue.prototype.$axios = axios
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
