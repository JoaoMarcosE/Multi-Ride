import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    starting: null,
    destination: null
  },
  getters: {
    starting: state => state.starting,
    destination: state => state.destination
  },
  mutations: {
    updateStarting(state, starting) {
      Vue.set(state, "starting", starting);
    },
    updateDestination(state, destination) {
      Vue.set(state, "destination", destination);
    }
  },
  actions: {
    async fetch_starting({ commit }, location) {
      try {
        debugger;

        commit("updateStarting", location);
      } catch (e) {
        console.error(e);
      }
    },
    async fetch_destination({ commit }, location) {
      try {
        debugger;

        commit("updateDestination", location);
      } catch (e) {
        console.error(e);
      }
    }
  }
});
