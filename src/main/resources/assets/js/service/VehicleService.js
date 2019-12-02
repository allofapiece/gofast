import store from 'store/store'
import VehicleApi from "../api/vehicle";

export class VehicleService {
    constructor(props) {
        this.api = new VehicleApi(store)
    }

    getAll() {
        return this.api.getAll()
    }

    sync() {
        return this.getAll().then((result) => {
            store.commit('vehicle/vehicles', result.data._embedded.vehicles)
            return Promise.resolve(result)
        })
    }
}

export default new VehicleService();
