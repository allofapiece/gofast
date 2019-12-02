
export default {
    namespaced: true,
    state: {
        vehicles: []
    },
    getters: {
        vehicles(state) {
            return state.vehicles
        },
    },
    mutations: {
        vehicles(state, vehicles) {
            state.vehicles = vehicles
        },
        addVehicle(state, vehicle) {
            state.vehicles.push(vehicle)
        },
        deleteVehicle(state, id) {
            state.vehicles.splice(state.vehicles.map(vehicle => vehicle.id).indexOf(id), 1)
        },
        reset(state) {
            state.vehicles = [];
        }
    },
    actions: {}
}
