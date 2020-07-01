import router from '../../router'
import cookies from 'vue-cookies'

const state = {
    terms: false,
    payment: false,
    paymentInfo: {
      parkingLotName: '',
      providerName: '',
      address: '',
      price: '',
    },
    tid: cookies.get('tid'),
  }
const getters = {
    terms: state => state.terms,
    payment: state => state.payment,
    paymentInfo: state => state.paymentInfo,
    tid: state => state.tid,
  }
const mutations = {
    setTerms: (state) => { state.terms = !state.terms },
    setPayment: (state) => { state.payment = !state.payment },
    setpaymentInfo: (state, data) => {
      state.paymentInfo.parkingLotName = data.name
      state.paymentInfo.providerName = data.uid
      state.paymentInfo.address = data.address
      state.paymentInfo.price = data.price
    },
    setTid: (state, data) => {
      state.tid = data
    },
  }
const actions = {
  confirmLogin: ({ commit, rootGetters }) => {
    if (!rootGetters.isLoggedIn) {
      alert('로그인이 필요한 서비스 입니다.')
      router.push('/login')
    } else {
      commit('setTerms')
    }
  },
  createTidCookie: ({ commit }, tid) => {
    commit('seTid', tid)
    cookies.set('tid', tid)
  },
}
export default { state, getters, mutations, actions }
