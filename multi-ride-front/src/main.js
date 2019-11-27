import Vue from 'vue'
import App from './App.vue'
import store from './store';
import BootstrapVue from 'bootstrap-vue'
import Unicon from 'vue-unicons'
import { uniHistory } from 'vue-unicons/src/icons'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

Vue.config.productionTip = false

Unicon.add([uniHistory])
Vue.use(Unicon)
Vue.use(BootstrapVue)

new Vue({
  store,
  render: h => h(App),
}).$mount('#app')
