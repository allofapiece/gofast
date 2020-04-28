<template>
    <div>
        <v-container>
            <v-row>
                <v-data-table
                        style="width:100%"
                        :headers="headers"
                        :items="orders"
                        sort-by="calories"
                        class="elevation-1"
                >
                    <template v-slot:item.action="{ item }">
                        <Knapsack :orders="orders" :item="item"></Knapsack>
                    </template>
                </v-data-table>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import orderService from 'service/OrderService'
    import Knapsack from "./Knapsack.vue";

    export default {
        components: {
            Knapsack
        },
        data: () => ({
            orders: [],
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
                { text: 'Actions', value: 'action', sortable: false },
            ],
        }),
        computed: {
        },
        methods: {
            knapsack(item) {

            },
            loadOrders() {
                orderService.getForCurrentCompany().then((result) => {
                    this.orders = result.data.content
                })
            }
        },
        created() {
            if (this.$store.getters['profile/profile']) {
                this.loadOrders()
            } else {
                this.$store.subscribe((mutation) => {
                    if (mutation.type === 'profile/profile') {
                        this.loadOrders()
                    }
                })
            }
        }
    }
</script>
