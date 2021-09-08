export default {
  namespaced: true,

  state: {
    showMobileMenu: false,
  },

  mutations: {
    TWICE_MOBILE_MENU(state, value = true) {
      state.showMobileMenu = value;
    },
  },
};
