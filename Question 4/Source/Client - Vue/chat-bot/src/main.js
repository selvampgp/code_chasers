import Vue from 'vue'
import App from './App.vue'
import Chat from "vue-beautiful-chat";
import store from "./store";

Vue.use(Chat);

new Vue({
  store,
  render: h => h(App),
}).$mount('#app')
