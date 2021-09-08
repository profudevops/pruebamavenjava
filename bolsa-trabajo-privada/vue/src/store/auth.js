import router from '../router';
import authSrv from '../services/auth';

const token = sessionStorage.getItem('token');
const initialState = token ? {
  status: { loggedIn: true },
  token,
} : {
  status: null,
  token: null,
};

export default {
  namespaced: true,
  state: initialState,

  actions: {

    initAuth({ commit }) {
      commit('logout');
    },

    login({ commit }, { usuario, password }) {
      commit('loginRequest', { usuario });

      return authSrv.login({
        usuario,
        password,
      })
        .then(({ data: { entity } }) => {
          sessionStorage.setItem('token', entity.token);
          sessionStorage.setItem('user', JSON.stringify(entity));
          commit('loginSuccess', entity);
          router.push({ name: 'dashboard' });
        })
        .catch((error) => {
          commit('loginFailure', error);

          throw error;
        });
    },

    logout({ commit }) {
      authSrv.logout();
      commit('logout');
    },

    refreshToken({ commit }, refreshToken) {
      sessionStorage.setItem('token', refreshToken);
      commit('loginSuccess', refreshToken);
    },
  },

  mutations: {
    loginRequest(state, currToken) {
      state.status = { loggingIn: true };
      state.token = currToken;
    },
    loginSuccess(state, currToken) {
      state.status = { loggedIn: true };
      state.token = currToken;
    },
    loginFailure(state, err) {
      state.status = err;
      state.token = null;
    },
    logout(state) {
      state.status = null;
      state.token = null;
    },
  },

  getters: {
    token: state => state.token,
    status: state => state.status,
  },
};
