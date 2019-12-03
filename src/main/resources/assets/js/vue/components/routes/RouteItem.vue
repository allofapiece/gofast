<template>
    <v-card class="mb-3">
        <v-card-text class="d-flex align-center">
            <div class="mr-3">{{route.to.address}}</div>
            <v-icon class="mr-3">compare_arrows</v-icon>
            <div>{{route.from.address}}</div>
            <v-select
                    @change="onChange"
                    :items="items"
                    v-model="vehicle"
                    label="Vehicle"
                    solo
                    dense
                    class="ml-auto vehicle-select"
            ></v-select>
            <v-btn class="ml-2 primary red">Unbind</v-btn>
        </v-card-text>
    </v-card>
</template>

<script>
    import {mapState} from "vuex";
    import vehicle from "../../../store/modules/vehicle";

    import routeService from 'service/RouteService'
    import alertService from 'alert/alert-service'

    export default {
        data() {
            return {
                vehicle: this.route.vehicles.length
                    ? this.route.vehicles[0].name
                    : ''
            }
        },
        props: {
            route: Object
        },
        computed: {
            ...mapState('vehicle', ['vehicles']),
            items: {
                get() {
                    return this.vehicles.map(vehicle => vehicle.name)
                },
                set(value) {
                    this.vehicle = value
                }
            }
        },
        methods: {
            onChange() {
                routeService.update(this.route.id, {
                    vehicles: this.vehicles
                        .filter(vehicle => vehicle.name === this.vehicle)
                        .map(vehicle => `/${vehicle.id}`)
                }).then(() => {alertService.push({message: 'Changes accepted.'})})
            }
        }
    }
</script>
<style>
    .vehicle-select {
        max-width: 200px;
    }
    .vehicle-select [role="button"] {
        margin: 0 !important;
    }
    .vehicle-select .v-text-field__details {
        display: none;
    }
</style>
