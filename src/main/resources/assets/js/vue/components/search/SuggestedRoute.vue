<template>
    <v-card class="mb-3">
        <v-card-text class="d-flex align-center">
            <div v-for="(point, i) in suggest" :key="point.from.id" class="d-flex align-center justify-space-between"   >
                <div v-if="i === 0" class="mr-3">
                    <router-link :to="`/points/${point.from.id}`">{{point.from.address}}</router-link>
                </div>
                <v-icon class="mr-3">arrow_right_alt</v-icon>
                <div class="mr-3">
                    <router-link :to="`/points/${point.to.id}`">{{point.to.address}}</router-link>
                </div>
            </div>
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        props: {
            suggest: Array,
        },
        components: {
        },
        data: () => ({
        }),
        computed: {
        },
        methods: {
        },
    }
</script>
