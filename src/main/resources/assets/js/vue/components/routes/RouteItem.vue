<template>
    <v-card class="mb-3">
        <v-card-text class="d-flex align-center">
            <div class="mr-3">{{route.to.address}}</div>
            <v-icon class="mr-3">compare_arrows</v-icon>
            <div>{{route.from.address}}</div>
            <v-btn class="ml-auto primary red">Unbind</v-btn>
        </v-card-text>
    </v-card>
</template>

<script>

    export default {
        props: {
            route: Object
        },
    }
</script>
