import axios from 'axios';
import { assign } from 'lodash';

const serverUrls = "http://10.100.8.131:8081/chatbot";
const state = {
  serverUrl: serverUrls,
};

const getUrl = (url) => [state.serverUrl, url].join('/');

const RAW = {
  GET: axios.get,
  POST: axios.post,
  PUT: axios.put,
  PATCH: axios.patch,
  DELETE: axios.delete,
};

// exported methods
async function GET(url) {
  const URL = getUrl(url);
  try {
    const response = await axios.get(URL, assign({}));
    return response.data
  } catch (e) {
    throw new Error(e);
  }
}


async function POST(url, params) {
  const URL = getUrl(url);
  try {
    const response = await axios.post(URL, params, {
    });
    return response.data;
  } catch (e) {
    throw new Error(e);
  }
}

export {RAW, GET, POST};
