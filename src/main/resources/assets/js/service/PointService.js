import store from 'store/store'
import PointApi from "../api/point";

export class PointService {
    constructor(props) {
        this.api = new PointApi(store)
    }

    get(id){
        return this.api.get(id);
    }

    getByUserId(id) {
        return this.api.getByUserId(id)
    }

    add(data) {
        return this.api.add(data).then(result => {
            store.commit('point/addPoint', result.data)
            return result
        })
    }

    search(search, from) {
        return this.api.getAllLike(search, from)
    }

    sync() {
        this.getByUserId().then(result => {
            store.commit('point/points', result.data._embedded.points)
        })
    }
}

export default new PointService();
