const state = {
  selectedParkingLot: false,
};
const getters = {
  isPicked: (state) => state.selectedParkingLot,
};

const mutations = {
  setParkingLot: (state) => {
    state.selectedParkingLot = !state.selectedParkingLot;
    console.log(state.selectedParkingLot);
    return state.selectedParkingLot;
  },
};
const actions = {};

export default { state, getters, mutations, actions };
