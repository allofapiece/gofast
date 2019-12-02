import store from 'store/store'
import RouteApi from "../api/route";

export class RouteService {
    constructor(props) {
        this.api = new RouteApi(store)
    }

    get(id){
        return this.api.get(id);
    }

    getByPointId(id) {
        return this.api.getByPointId(id)
    }

    getByUserId(id) {
        return this.api.getByUserId(id)
    }

    create(from, to) {
        return this.api.post(from, to, store.getters['profile/profile'].id)
    }

    deleteAll(ids) {
        return this.api.deleteAll(ids)
    }
}

export default new RouteService();
