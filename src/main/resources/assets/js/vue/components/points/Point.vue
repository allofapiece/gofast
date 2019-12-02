<template>
    <v-container>
        <v-card class="my-3">
            <v-card-text class="d-flex align-center">
                <div class="address">
                    <h3>{{ point.address }}</h3>
                </div>
                <PointBind :points="points" :point="point"></PointBind>
            </v-card-text>
        </v-card>
        <h1>Related Points</h1>
        <v-divider class="mb-2"></v-divider>
        <RoutesList :routes="thisRoutes"></RoutesList>
    </v-container>
</template>

<script>
    import pointService from 'service/PointService'
    import routeService from 'service/RouteService'
    import RoutesList from "../routes/RoutesList.vue";
    import PointBind from "./PointBind.vue";
    import {mapState} from "vuex";

    export default {
        components: {PointBind, RoutesList},
        data() {
            return {
                point: {},
            }
        },
        computed: {
            ...mapState('point', ['points']),
            ...mapState('route', ['routes']),
            thisRoutes() {
                return routeService.getRoutesByPointId(this.$route.params.id)
            }
        },
        mounted() {
            pointService.get(this.$route.params.id).then((result) => {
                this.point = result.data
            })
        },
        beforeMount() {
            this.$store.subscribe((mutation) => {
                if (mutation.type === 'profile/profile') {
                    pointService.sync()
                    routeService.sync()
                }
            })
        }
    }
</script>
