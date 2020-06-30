import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import './plugins'
import store from './store'
import * as VueGoogleMaps from 'vue2-google-maps'
import VueCookies from 'vue-cookies'

Vue.use(VueGoogleMaps, {
  load: {
    key: process.env.VUE_APP_GEO_API_KEY,
    libraries: 'places', // necessary for places input
  },
})

Vue.use(VueCookies)

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
