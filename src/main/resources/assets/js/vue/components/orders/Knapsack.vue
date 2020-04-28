<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
                <v-icon>work</v-icon>
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">Knapsack Problem</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <h2 style="width: 100%" class="mb-2">Vehicle Capacity</h2>
                        <p>{{ this.item.vehicle.capacity }}</p>
                    </v-row>
                    <v-row>
                        <v-col cols="12">
                            <h3 class="mb-2">All Orders From This Point</h3>
                            <v-data-table
                                    :headers="headers"
                                    :items="pointOrders"
                                    sort-by="calories"
                                    class="elevation-1"
                            >
                            </v-data-table>
                        </v-col>
                    </v-row>
                    <v-row v-if="bagOrders.length > 0">
                        <v-col cols="12">
                            <h3 class="mb-2">The Best Delivery Option</h3>
                            <v-data-table
                                    :headers="headers"
                                    :items="bagOrders"
                                    sort-by="calories"
                                    class="elevation-1"
                            >
                            </v-data-table>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                <v-btn color="green darken-1" text @click="calculate">Calculate Next Delivery</v-btn>
                <v-btn color="green darken-1" text @click="submit">Approve Selected</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import orderService from 'service/OrderService'
    import order from "../../../api/order";

    export default {
        components: {},
        props: {
            orders: Array,
            item: Object,
        },
        data: () => ({
            bagOrders: [],
            dialog: false,
            headers: [
                {
                    text: 'Order Id',
                    align: 'left',
                    value: 'id',
                },
                { text: 'User', value: 'user.fullName' },
                { text: 'Start Point', value: 'from.address' },
                { text: 'Weight (kg)', value: 'weight' },
                { text: 'Price for kg', value: 'cargo.price' },
                { text: 'Cargo', value: 'cargo.name' },
            ],
        }),
        computed: {
            pointOrders() {
                return this.orders.filter(order => order.from.id === this.item.from.id)
            }
        },
        methods: {
            submit() {
                this.dialog = false
            },
            calculate() {
                orderService.calculate(this.item.id).then((result) => {
                    this.bagOrders = result.data.content
                })
            },
        },
        mounted() {
        },
    }
</script>
