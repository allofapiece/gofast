<template>
    <div>
        <v-container>
            <v-row>
                <v-col cols="12" sm="6">
                    <v-select
                            @change="changeVehicle"
                            :items="vehicles"
                            item-text="name"
                            item-value="id"
                            v-model="vehicle"
                            label="Vehicle"
                            solo
                            dense
                            class="vehicle-select"
                    ></v-select>
                </v-col>
            </v-row>
            <v-row>
                <SearchPoint @change="changeFrom" label="Start Point"></SearchPoint>
                <SearchPoint @change="changeTo" label="Finish Point" :model="from"></SearchPoint>
            </v-row>
            <v-divider></v-divider>
            <v-row>
                <SuggestedRoutes :vehicle="vehicle" :suggests="suggests"></SuggestedRoutes>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import SearchPoint from "./SearchPoint.vue";
    import SuggestedRoutes from "./SuggestedRoutes.vue";
    import routeService from "service/RouteService"
    import {mapState} from "vuex";

    export default {
        components: {
            SearchPoint,
            SuggestedRoutes
        },
        data: () => ({
            to: null,
            from: null,
            suggests: [],
            vehicle: 1
        }),
        computed: {
            ...mapState('vehicle', ['vehicles']),
        },
        methods: {
            changeFrom(val) {
                this.from = val

                if (this.to && this.from && this.vehicle) {
                    this.suggest()
                }
            },
            changeTo(val) {
                this.to = val

                if (this.to && this.from && this.vehicle) {
                    this.suggest()
                }
            },
            changeVehicle(val) {
                if (this.to && this.from && this.vehicle) {
                    this.suggest()
                }
            },
            suggest() {
                return routeService.suggest(
                    this.from.id,
                    this.to.id,
                    this.vehicle
                ).then((res) => {
                    this.suggests = res.data.content
                })
            }
        },
    }
</script>
