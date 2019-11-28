<template>
    <v-container>
        <PointCreate></PointCreate>
        <PointsList :points="this.points"></PointsList>
    </v-container>
</template>

<script>
    import PointsList from './PointsList.vue'
    import PointCreate from './PointCreate.vue'
    import pointService from "../../../service/PointService";
    import store from 'vuex'
    import {mapState} from 'vuex'

    export default {
        components: {
            PointsList,
            PointCreate
        },
        computed: {
            ...mapState('point', ['points'])
        },
        beforeMount() {
            pointService.sync()
        }
    }
</script>
