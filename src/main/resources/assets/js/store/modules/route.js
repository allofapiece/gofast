
export default {
    namespaced: true,
    state: {
        routes: []
    },
    getters: {
        routes(state) {
            return state.routes
        },
    },
    mutations: {
        routes(state, routes) {
            state.routes = routes
        },
        addRoute(state, route) {
            state.routes.push(route)
        },
        deleteRoute(state, id) {
            state.routes.splice(state.routes.map(route => route.id).indexOf(id), 1)
        },
        reset(state) {
            state.routes = [];
        }
    },
    actions: {}
}
