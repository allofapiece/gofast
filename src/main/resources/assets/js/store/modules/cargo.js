export default {
    namespaced: true,
    state: {
        cargos: [],
    },
    getters: {
        cargos(state) {
            return state.cargos
        },
    },
    mutations: {
        cargos(state, cargos) {
            state.cargos = cargos
        },
        addCargo(state, cargo) {
            state.cargos.push(cargo)
        },
        reset(state) {
            state.cargos = [];
        },
    },
    actions: {}
}
