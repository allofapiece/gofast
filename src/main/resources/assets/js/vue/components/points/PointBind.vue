<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
            <v-btn color="primary" class="ml-auto" dark v-on="on">Bind Point</v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">Point Binding</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-autocomplete
                                    label="Points"
                                v-model="values"
                                :items="items"
                                chips
                                small-chips
                                multiple
                                solo
                        ></v-autocomplete>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                <v-btn color="blue darken-1" text @click="submit">Bind</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import routeService from 'service/RouteService'
    import RoutesList from "../routes/RoutesList.vue";
    import {mapState} from "vuex";
    import route from "../../../api/route";
    import pointService from "../../../service/PointService";

    export default {
        components: {RoutesList},
        props: {
            points: Array,
            point: {}
        },
        data() {
            return {
                dialog: false,
                tValues: []
            }
        },
        computed: {
            ...mapState('route', ['routes']),
            items() {
                return this.points
                    .filter(point => point.id !== parseInt(this.$route.params.id))
                    .map(point => point.address)
            },
            thisRoutes() {
                return routeService.getRoutesByPointId(this.$route.params.id)
            },
            values: {
                get() {
                    return this.thisRoutes.map(route => route.to.id === parseInt(this.$route.params.id)
                        ? route.from.address
                        : route.to.address
                    )
                },
                set(values) {
                    this.tValues = values
                }

            }
        },
        methods: {
            submit() {
                const toIds = this.thisRoutes.map(route => route.to.id)
                const fromIds = this.thisRoutes.map(route => route.from.id)

                const ids = this.points
                    .filter(point => this.tValues.includes(point.address))
                    .map(point => point.id)

                const toDelete = this.thisRoutes
                    .filter(route => !ids.includes(route.to.id) && !ids.includes(route.from.id))
                    .map(route => route.id)

                const toCreate = ids.filter(id => !toIds.includes(id) && !fromIds.includes(id))

                routeService.deleteAll(toDelete)
                routeService.createAll(this.$route.params.id, toCreate)
            }
        },
        mounted() {
            /*let $this = this

            this.$store.subscribe((mutation) => {
                if (mutation.type === 'profile/profile') {
                    routeService.sync().then(result => {
                        $this.routes = routeService.getRoutesByPointId(this.$route.params.id)
                        this.values = $this.routes.map(route => route.to.id === parseInt($this.$route.params.id)
                            ? route.from.address
                            : route.to.address
                        )
                    })
                }
            })*/

            /*const routes = routeService.getByPointId(this.$route.params.id).then((result) => {
                this.routes = result.data.content
                this.values = result.data.content.map(point => point.to.id === parseInt($this.$route.params.id)
                    ? point.from.address
                    : point.to.address
                )
            })*/
        },
    }
</script>
