import Cookies from 'js-cookie'

export default {
    namespaced: true,
    state: {
        points: [],
        from: null,
        to: null
    },
    getters: {
        points(state) {
            return state.points
        },
    },
    mutations: {
        points(state, points) {
            state.points = points
        },
        addPoint(state, point) {
            state.points.push(point)
        },
        reset(state) {
            state.points = [];
        },
        setFrom(state, from) {
            state.from = from
        },
        setTo(state, from) {
            state.from = from
        }
    },
    actions: {}
}
