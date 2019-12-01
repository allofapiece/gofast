<template>
    <div>
        <RouteItem v-for="route in this.routes" :route="route" :key="route.id"></RouteItem>
    </div>
</template>

<script>
    import RouteItem from "./RouteItem.vue";

    export default {
        components: {
            RouteItem
        },
        props: {
            routes: Array
        },
    }
</script>
