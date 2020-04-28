<template>
    <v-container>
        <SuggestedRoute v-for="(suggest, i) in suggests" :key="i" :suggest="suggest" :vehicle="vehicle"></SuggestedRoute>
    </v-container>
</template>

<script>
    import SuggestedRoute from "./SuggestedRoute.vue";

    export default {
        props: {
            suggests: Array,
            vehicle: Number,
        },
        components: {
            SuggestedRoute
        },
        data: () => ({
        }),
        computed: {
        },
        methods: {
        },
    }
</script>
