import Vue from 'vue'
import Vuex from 'vuex'
import profile from './modules/profile'
import auth from './modules/auth'
import point from "./modules/point";

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        'profile': profile,
        'auth': auth,
        'point': point,
    },
})
