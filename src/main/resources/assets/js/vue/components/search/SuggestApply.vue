<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
            <v-btn icon class="ml-auto" v-on="on">
                <v-icon>done</v-icon>
            </v-btn>
        </template>
        <v-card>

        </v-card>
    </v-dialog>
</template>

<script>

    export default {
        components: {},
        props: {
        },
        data() {
            return {
                dialog: false,
            }
        },
        computed: {

        },
        methods: {
        },
        mounted() {
        },
    }
</script>
