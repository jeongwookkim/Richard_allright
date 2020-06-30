import Vue from 'vue'
import Vuex from 'vuex'
import ParkingLot from './modules/parkingLot'
import Auth from './modules/auth'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: { ParkingLot, Auth },
})
