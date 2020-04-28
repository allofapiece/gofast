<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
            <v-btn icon class="ml-auto" v-on="on">
                <v-icon>done</v-icon>
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">Delivery Arrangement</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field
                                    label="Weight"
                                    solo
                                    type="number"
                                    v-model="weight"
                            ></v-text-field>
                            <v-autocomplete
                                    autocomplete="off"
                                    label="Cargo"
                                    v-model="cargo"
                                    item-text="name"
                                    item-value="id"
                                    :items="cargos"
                                    solo
                            ></v-autocomplete>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                <v-btn color="blue darken-1" text @click="submit">Deliver</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import orderService from 'service/OrderService'
    import {mapState} from "vuex";
    import alertService from "../../../alert/alert-service";

    export default {
        components: {},
        props: {
            from: Object,
            vehicle: Number
        },
        data() {
            return {
                dialog: false,
                weight: 0,
                cargo: null
            }
        },
        computed: {
            ...mapState('cargo', ['cargos']),
            ...mapState('profile', ['profile']),
        },
        methods: {
            submit() {
                orderService.create({
                    weight: this.weight,
                    cargo: `/${this.cargo}`,
                    user: `/${this.profile.id}`,
                    from: `/${this.from.id}`,
                    vehicle: `/${this.vehicle}`
                }).then(() => {
                    alertService.push('Order sent.')
                    this.dialog = false
                })
            }
        },
        mounted() {

        },
    }
</script>
