import Api from './api'

export default class OrderApi extends Api {
    constructor(store) {
        super(store)

        this.instance = this.builder.withConfig({
            baseURL: '/api/orders',
        }).build()
    }

    get(id) {
        return this.instance.get(id)
    }

    getAll() {
        return this.instance.get('')
    }

    create(data) {
        return this.instance.post('', data)
    }
}
