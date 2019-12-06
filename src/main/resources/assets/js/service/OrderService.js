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

    create(data) {
        return this.api.create(data)
    }
}

export default new OrderService();
