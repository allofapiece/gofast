import store from 'store/store'
import OrderApi from "../api/order";

export class OrderService {
    constructor(props) {
        this.api = new OrderApi(store)
    }

    get(id) {
        return this.api.get(id)
    }

    getAll() {
        return this.api.getAll()
    }

    getForCurrentCompany() {
        const profile = store.getters['profile/profile']
        return this.api.getByCompanyId(profile.id)
    }

    create(data) {
        return this.api.create(data)
    }

    calculate(id) {
        return this.api.calculate(id)
    }
}

export default new OrderService();
