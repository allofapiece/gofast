import store from 'store/store'
import RouteApi from "../api/route";
import empty from 'is-empty'

export class RouteService {
    constructor(props) {
        this.api = new RouteApi(store)
    }

    get(id){
        return this.api.get(id);
    }

    getByPointId(id) {
        return this.api.getByPointId(id)
    }

    getRoutesByPointId(id) {
        return store.getters['route/routes']
            .filter(route => route.to.id == id || route.from.id ==id)
    }

    getByUserId(id) {
        return this.api.getByUserId(id)
    }

    create(from, to) {
        return this.api.post(from, to, store.getters['profile/profile'].id)
            .then((result) => {
                let data = result.data._embedded
                const link = result.data._links.self.href

                data.id = parseInt(link.substr(link.lastIndexOf('/') + 1))
                store.commit('route/addRoute', data)
            })
    }

    createAll(from, tos) {
        if (empty(tos)) {
            return
        }

        tos.forEach(to => this.create(from, to), this)
    }

    deleteAll(ids) {
        ids.forEach(id => store.commit('route/deleteRoute', id))
        return empty(ids) ? Promise.resolve(false) : this.api.deleteAll(ids)
    }

    update(id, data) {
        return this.api.patch(id, data)
    }

    suggest(from, to) {
        return this.api.suggest(from, to)
    }

    sync() {
        return this.getByUserId().then(result => {
            store.commit('route/routes', result.data.content)
            return Promise.resolve(result);
        })
    }
}

export default new RouteService();
