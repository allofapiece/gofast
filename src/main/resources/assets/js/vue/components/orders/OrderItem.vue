<template>
    <v-card class="mb-3">
        <v-card-text>
            {{ order.user.fullName }}
        </v-card-text>
    </v-card>
</template>

<script>

    export default {
        props: {
            order: Object
        },
    }
</script>
