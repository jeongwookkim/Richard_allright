import router from '../../router'
import cookies from 'vue-cookies'

const state = {
  uid: cookies.get('user_uid'),
  userInfo: {
    userPhotoUrl: cookies.get('userPhotoUrl'),
  },
}

const getters = {
  isLoggedIn: (state) => !!state.uid,
  userPhotoUrl: (state) => state.userInfo.userPhotoUrl,
}

const mutations = {
  setuId (state, uid) {
    state.uid = uid
  },
  setUserInfo (state, user) {
    state.userPhotoUrl = user.photoURL
    cookies.set('userPhotoUrl', user.photoURL)
  },
}

const actions = {
  logout ({ commit }) {
    // uid 값 null로 바꾸기
    commit('setuId', null)
    cookies.remove('user_uid')
    cookies.remove('userPhotoUrl')
    location.pathname = '/'
  },
  login ({ commit }, user) {
    commit('setuId', user.uid)
    commit('setUserInfo', user)
    cookies.set('user_uid', user.uid)
    router.push('/')
  },
}

export default {
  state,
  getters,
  mutations,
  actions,
}
