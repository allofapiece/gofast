import Vue from 'vue'
import Vuex from 'vuex'
import profile from './modules/profile'
import auth from './modules/auth'
import point from "./modules/point";
import route from "./modules/route";
import cargo from "./modules/cargo";
import vehicle from "./modules/vehicle";

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        'profile': profile,
        'auth': auth,
        'point': point,
        'route': route,
        'vehicle': vehicle,
        'cargo': cargo,
    },
})
