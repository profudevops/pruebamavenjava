import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth';
import vacancy from './vacancy';
import general from './general';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    vacancy,
    general,
  },
});
