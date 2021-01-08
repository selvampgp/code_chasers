import Vuex from "vuex";
import Vue from "vue";
import chatBot from "./chatBot";

Vue.use(Vuex);

const modules = {
  chatBot,
};

export default function() {
  const Store = new Vuex.Store({ modules });
  return Store;
}