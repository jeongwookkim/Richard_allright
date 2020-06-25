const state = {
  terms: false,
  payment: false
};
const getters = {
  terms: state => state.terms,
  payment: state => state.payment
};

const mutations = {
  setTerms: state => {
    state.terms = !state.terms;
    console.log(state.terms);
    console.log("밑에는 겟터");
    return state.terms;
  },
  setPayment: state => {
    return (state.payment = !state.payment);
  }
};
const actions = {};

export default { state, getters, mutations, actions };
