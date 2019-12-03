<template>
    <v-col cols="12" sm="6">
        <v-autocomplete
                autocomplete="off"
                v-model="model"
                :items="items"
                :loading="isLoading"
                :search-input.sync="search"
                hide-no-data
                hide-selected
                item-text="address"
                item-value="id"
                :label="label"
                :placeholder="label"
                return-object
                solo
                @change="$emit('change', model)"
        ></v-autocomplete>
        <v-divider></v-divider>
        <v-expand-transition>
            <PointItem v-if="model" :point="model"></PointItem>
        </v-expand-transition>
    </v-col>
</template>

<script>
    import pointService from 'service/PointService'
    import PointItem from "../points/PointItem.vue";

    export default {
        components: {
            PointItem
        },
        props: {
            label: {
                type: String,
                default: 'Point'
            },
            selected: Object,
        },
        data: () => ({
            descriptionLimit: 60,
            entries: [],
            isLoading: false,
            search: null,
            model: null
        }),
        computed: {
            items () {
                return this.entries
            },
        },
        watch: {
            search (val) {
                if (this.isLoading) return

                this.isLoading = true

                pointService.search(val, this.selected)
                    .then(res => {
                        this.entries = res.data.content
                    }).catch(err => {
                    console.log(err)
                }).finally(() => (this.isLoading = false))
            },
        }
    }
</script>