import Api from './api'

export default class CargoApi extends Api {
    constructor(store) {
        super(store)

        this.instance = this.builder.withConfig({
            baseURL: '/api/cargos',
        }).build()
    }

    get(id) {
        return this.instance.get(id)
    }

    getAll() {
        return this.instance.get('')
    }
}
