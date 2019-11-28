<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
            <v-btn color="primary" class="mb-3" dark v-on="on">Add New Point</v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">New Point</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field v-model="address" label="Address" required></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                <v-btn color="blue darken-1" text @click="submit">Save</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import pointService from 'service/PointService'

    export default {
        data() {
            return {
                address: '',
                dialog: false
            }
        },
        methods: {
            submit() {
                // this.dialog = false
                pointService.add({
                    address: this.address
                }).then(() => {
                    this.dialog = false
                })
            }
        }
    }
</script>