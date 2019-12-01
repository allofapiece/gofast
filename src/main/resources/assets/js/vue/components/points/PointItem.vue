<template>
    <v-card class="mb-3" @click="$router.push(`/points/${point.id}`)">
        <v-card-text>
            {{ point.address }}
        </v-card-text>
    </v-card>
</template>

<script>

    export default {
        props: {
            point: Object
        },
    }
</script>
