import Vue from 'vue'
import App from './App'
import util from 'util.js'

Vue.config.productionTip = false
Vue.prototype.jump = util.jump
Vue.prototype.get = util.get
Vue.prototype.post = util.post
Vue.prototype.msg = util.msg

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
