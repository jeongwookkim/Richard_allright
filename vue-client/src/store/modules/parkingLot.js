const state = {
    terms: false,
    payment: false,
    paymentInfo: {
      parkingLotName: '',
      providerName: '',
      address: '',
      price: '',
    },
  }
const getters = {
    terms: state => state.terms,
    payment: state => state.payment,
    paymentInfo: state => state.paymentInfo,
  }
const mutations = {
    setTerms: (state) => { state.terms = !state.terms },
    setPayment: (state) => { state.payment = !state.payment },
    setpaymentInfo: (state, data) => {
      state.paymentInfo.parkingLotName = data.name
      state.paymentInfo.providerName = data.uid
      state.paymentInfo.address = data.address
      state.paymentInfo.price = data.price
      return state.paymentInfo
    },
  }
const actions = {}
export default { state, getters, mutations, actions }
