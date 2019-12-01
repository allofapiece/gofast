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
}
