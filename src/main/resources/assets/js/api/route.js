import Api from './api'

export default class RouteApi extends Api {
    constructor(store) {
        super(store)

        this.instance = this.builder.withConfig({
            baseURL: '/api/routes',
        }).build()
    }

    get(id) {
        return this.instance.get(id)
    }

    getByPointId(id) {
        return this.instance.get('/search/pointId', {
            params: {
                id: id
            }
        })
    }

    getByUserId(id) {
        if (!id) {
            id = this.store.getters['profile/profile'].id
        }

        return this.instance.get('search/userId', {
            params: {
                id: id
            }
        })
    }

    post(from, to, company) {
        return this.instance.post('', {
            from: `/${from}`,
            to: `/${to}`,
            company: `/${company}`
        })
    }

    patch(id, data) {
        return this.instance.patch('' + id, data)
    }

    delete(id) {
        return this.instance.delete('' + id)
    }

    deleteAll(ids) {
        ids.forEach(this.delete, this)
    }

    suggest(from, to, vehicle) {
        return this.instance.get('/search/suggest', {
            params: {
                from: from,
                to: to,
                vehicle: vehicle
            }
        })
    }
}
