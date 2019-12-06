<template>
    <div>
        <Order v-for="order in this.orders" :order="order" :key="order.id"></Order>
    </div>
</template>

<script>
    import Order from './OrderItem.vue'

    export default {
        components: {
            Order
        },
        props: {
            points: Array
        },
    }
</script>
