import Api from './api'

export default class PointApi extends Api {
    constructor(store) {
        super(store)

        this.instance = this.builder.withConfig({
            baseURL: '/api/points',
        }).build()
    }

    get(id) {
        return this.instance.get(id)
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

    add(data) {
        return this.instance.post('/', data)
    }
}
