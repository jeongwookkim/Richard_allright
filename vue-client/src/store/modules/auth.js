import qs from 'qs'
import router from '../../router'
import cookies from 'vue-cookies'

const state = {
  token: cookies.get('user_token'),
}

const getters = {
  isLoggedIn: (state) => !!state.token,
}

const mutations = {
  setToken (state, token) {
    state.token = token
  },
}

const actions = {
  logout ({ commit }) {
    // Token 값 null로 바꾸기
    commit('setToken', null)
    cookies.remove('user_token')
    location.pathname = '/'
  },
  login () {
    const ROOT_URL = 'https://api.user.com'
    const CLIENT_ID = process.env.VUE_APP_CLIENT_ID
    const queryString = {
      client_id: CLIENT_ID,
      response_type: 'token',
    }
    const fullUrl = `${ROOT_URL}/oauth2/authorize?${qs.stringify(queryString)}`
    window.location.href = fullUrl
  },
  finalizeLogin ({ commit }, hashString) {
    const queryObject = qs.parse(hashString.replace('#', ''))
    commit('setToken', queryObject.access_token)
    cookies.set('user_token', queryObject.access_token)
    router.push('/')
  },
}

export default {
  state,
  getters,
  mutations,
  actions,
}
