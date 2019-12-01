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
}

export default new RouteService();
