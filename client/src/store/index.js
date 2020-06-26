import Vue from "vue";
import Vuex from "vuex";
import ParkingLot from "./modules/parkingLot";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: { ParkingLot }
});
