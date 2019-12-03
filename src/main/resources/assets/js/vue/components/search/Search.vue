<template>
    <div>
        <v-container>
            <v-row>
                <SearchPoint @change="changeFrom" label="Start Point"></SearchPoint>
                <SearchPoint @change="changeTo" label="Finish Point" :model="from"></SearchPoint>
            </v-row>
            <v-divider></v-divider>
            <v-row>
                <SuggestedRoutes :suggests="suggests"></SuggestedRoutes>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import SearchPoint from "./SearchPoint.vue";
    import SuggestedRoutes from "./SuggestedRoutes.vue";
    import routeService from "service/RouteService"

    export default {
        components: {
            SearchPoint,
            SuggestedRoutes
        },
        data: () => ({
            to: null,
            from: null,
            suggests: []
        }),
        computed: {
        },
        methods: {
            changeFrom(val) {
                this.to = val

                if (this.to && this.from) {
                    this.suggest()
                }
            },
            changeTo(val) {
                this.from = val

                if (this.to && this.from) {
                    this.suggest()
                }
            },
            suggest() {
                return routeService.suggest(
                    this.from.id,
                    this.to.id
                ).then((res) => {
                    this.suggests = res.data.content
                })
            }
        },
    }
</script>
