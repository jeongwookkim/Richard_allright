import router from '../../router'
import cookies from 'vue-cookies'

const state = {
  uid: cookies.get('user_uid'),
  userInfo: {},
}

const getters = {
  isLoggedIn: (state) => !!state.uid,
}

const mutations = {
  setuid (state, uid) {
    state.uid = uid
  },
}

const actions = {
  logout ({ commit }) {
    // uid 값 null로 바꾸기
    commit('setuid', null)
    cookies.remove('user_uid')
    location.pathname = '/'
  },
  login ({ commit }, uid) {
    commit('setuid', uid)
    cookies.set('user_uid', uid)
    router.push('/')
  },
}

export default {
  state,
  getters,
  mutations,
  actions,
}
