import Api from './api'

export default class VehicleApi extends Api {
    constructor(store) {
        super(store)

        this.instance = this.builder.withConfig({
            baseURL: '/api/vehicles',
        }).build()
    }

    getAll() {
        return this.instance.get('')
    }
}
