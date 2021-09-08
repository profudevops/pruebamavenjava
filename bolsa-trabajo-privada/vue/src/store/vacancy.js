export default {
  namespaced: true,

  state: {
    currentAction: null,
  },

  mutations: {
    SET_CURRENT_ACTION(state, action) {
      state.currentAction = action;
    },
  },
};
