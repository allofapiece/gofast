import store from 'store/store'
import PointApi from "../api/point";

export class PointService {
    constructor(props) {
        this.api = new PointApi(store)
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

    sync() {
        this.getByUserId().then(result => {
            store.commit('point/points', result.data._embedded.points)
        })
    }
}

export default new PointService();
