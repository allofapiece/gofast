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

    getByUserId(id) {
        return this.instance.get('search/userId', {
            params: {
                id: id
            }
        })
    }

    getByCompanyId(id) {
        return this.instance.get('search/companyId', {
            params: {
                id: id
            }
        })
    }

    getByCompanyIdAndPointId(companyId, pointId) {
        return this.instance.get('search/pointId', {
            params: {
                id: id
            }
        })
    }

    calculate(id) {
        return this.instance.get('calculate', {
            params: {
                id: id
            }
        })
    }

    create(data) {
        return this.instance.post('', data)
    }
}
