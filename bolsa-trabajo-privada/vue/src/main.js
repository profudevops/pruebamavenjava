import Vue from 'vue';
import App from './App.vue';
import BootstrapVue from 'bootstrap-vue';
import VeeValidate from 'vee-validate';
import Notifications from 'vue-notification';
import { VueMasonryPlugin } from 'vue-masonry';


import router from './router';
import store from './store/index';
import httpInterceptor from '@/interceptors/http';
import permission from '@/directives/permission';

Vue.config.productionTip = false;

Vue.use(BootstrapVue);
Vue.use(VeeValidate);
Vue.use(permission);
Vue.use(Notifications);
Vue.use(VueMasonryPlugin);

// HTTP Interceptor
httpInterceptor();

new Vue({
  router,
  store,
  render: h => h(App),
  created() {
    if (window.addEventListener && document.hidden) {
      window.addEventListener('storage', () => {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('user');
        this.$router.push('/');
      });
    }
  },
}).$mount('#app');
