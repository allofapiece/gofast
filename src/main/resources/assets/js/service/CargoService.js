import store from 'store/store'
import CargoApi from "../api/cargo";

export class CargoService {
    constructor(props) {
        this.api = new CargoApi(store)
    }

    get(id) {
        return this.api.get(id);
    }

    getAll() {
        return this.api.getAll()
    }

    sync() {
        this.getAll().then(result => {
            store.commit('cargo/cargos', result.data._embedded.cargos)
        })
    }
}

export default new CargoService();
