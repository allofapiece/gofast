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

    export default {
        components: {RoutesList},
        props: {
            points: Array,
            point: {}
        },
        data() {
            return {
                dialog: false,
                routes: [],
                values: [],
            }
        },
        computed: {
            items(){
                return this.points
                    .filter(point => point.id !== parseInt(this.$route.params.id))
                    .map(point => point.address)
            }
        },
        methods: {
            submit() {

            }
        },
        mounted() {
            let $this = this
            routeService.getByPointId(this.$route.params.id).then((result) => {
                this.routes = result.data.content
                this.values = result.data.content.map(point => point.to.id === parseInt($this.$route.params.id)
                    ? point.from.address
                    : point.to.address
                )
            })
        },
    }
</script>
