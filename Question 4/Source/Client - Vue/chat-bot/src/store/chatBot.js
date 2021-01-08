import { GET } from "./restLayer";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import moment from "moment";

const M = {
  SET_TOKEN: "SET_TOKEN",
  SET_MESSAGE: "SET_MESSAGE",
  SET_CLIENT: "SET_CLIENT",
};

const stateObj = {
  message: [],
  chatToken: "",
  client: "",
};
const getters = {
  chatMessage: (state) => state.message,
  chatToken: (state) => state.chatToken,
  chatClient: (state) => state.client,
};
const actions = {
  async tokenGenerator({ commit }) {
    const token = GET("user/session").then((token) => {
      if (token) {
        commit(M.SET_TOKEN, token);
      }
    });
    return token;
  },
  async createClient({ commit }) {
    const url = "http://10.100.8.131:8081/chatbot/bot/";
    const socket = new SockJS(url);
    const stompClient = Stomp.over(socket);
    let suggestions = [];
    stompClient.connect(
      {},
      () => {
        commit(M.SET_CLIENT, stompClient);
        stompClient.subscribe("/user/message/" + stateObj.chatToken, function(
          response
        ) {
          const { body } = response;
          if (body.includes("Yes/No")) {
            suggestions = ["Yes", "No"];
          } else if (body.includes("choose your options")) {
            suggestions = [
              "1. Previous interview status",
              "2. Upcoming interview details",
            ];
          }
          else{
            suggestions = [];
          }
          const message = {
            type: "text",
            author: "system",
            data: {
              text: body,
            },
            stamp: moment().fromNow(),
            suggestions: suggestions,
          };
          commit(M.SET_MESSAGE, message);
          console.log(message);
        });
      },
      (error) => {
        console.log("error", error);
      }
    );
  },
  async sendMessage({ commit }, msg) {
    const message = {
      type: "text",
      author: "me",
      data: {
        text: msg,
      },
      stamp: moment().fromNow(),
    };
    commit(M.SET_MESSAGE, message);
    const msgData = { message: msg };
    try {
      stateObj.client.send(
        "/app/" + stateObj.chatToken,
        JSON.stringify(msgData)
      );
    } catch (e) {
      console.log("error", e);
    }
  },
  async updateMessage({ commit }, payload) {
    commit(M.SET_MESSAGE, payload);
  },
  async clear({ commit }) {
    commit(M.SET_TOKEN, "");
    commit(M.SET_MESSAGE, "#$cHaTClEaR$");
    commit(M.SET_CLIENT, "");
  },
};
const mutations = {
  [M.SET_MESSAGE](state, message) {
    if (message === "#$cHaTClEaR$") {
      state.message = [];
    } else {
      state.message.push(message);
    }
  },
  [M.SET_TOKEN](state, chatToken) {
    state.chatToken = chatToken;
  },
  [M.SET_CLIENT](state, client) {
    state.client = client;
  },
};
export default {
  namespaced: true,
  actions,
  getters,
  mutations,
  state: stateObj,
};
