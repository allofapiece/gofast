<template>
    <div>
        <v-container>
            <v-row>
                <v-data-table
                        :headers="headers"
                        :items="orders"
                        sort-by="calories"
                        class="elevation-1"
                >
                    <template v-slot:item.action="{ item }">
                        <v-icon small @click="knapsack(item)">
                            work
                        </v-icon>
                    </template>
                </v-data-table>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import orderService from 'service/OrderService'

    export default {
        components: {
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
                { text: 'Weight (kg)', value: 'weight' },
                { text: 'Cargo', value: 'cargo.name' },
                { text: 'Actions', value: 'action', sortable: false },
            ],
        }),
        computed: {
        },
        methods: {
            knapsack(item) {

            }
        },
        created() {
            orderService.getForCurrentCompany().then((result) => {
                this.orders = result.data._embedded.orders
            })
        }
    }
</script>
