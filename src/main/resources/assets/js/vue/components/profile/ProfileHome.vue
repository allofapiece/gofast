<template>
    <v-container>
        <v-row>
            <v-col class="col-12 col-md-3">
                <v-navigation-drawer permanent class="elevation-4" style="width:100%" floating>
                    <template v-if="this.isCurrent">
                        <v-list dense nav>
                            <v-list-item link :to="`/${this.profile.slug}/general`">
                                <v-list-item-icon>
                                    <v-icon>face</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>Profile</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <template v-if="this.profile.isCompany">
                                <v-list-item link to="/points">
                                    <v-list-item-icon>
                                        <v-icon>rounded_corner</v-icon>
                                    </v-list-item-icon>
                                    <v-list-item-content>
                                        <v-list-item-title>Points</v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item link to="/orders">
                                    <v-list-item-icon>
                                        <v-icon>rounded_corner</v-icon>
                                    </v-list-item-icon>
                                    <v-list-item-content>
                                        <v-list-item-title>Orders</v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </template>
                            <v-list-item link to="/search">
                                <v-list-item-icon>
                                    <v-icon>search</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>Search</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item link to="/user/settings/general">
                                <v-list-item-icon>
                                    <v-icon>settings_input_component</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>Settings</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item link to="/user/edit/general">
                                <v-list-item-icon>
                                    <v-icon>create</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>Edit Profile</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                    </template>
                </v-navigation-drawer>
            </v-col>
            <v-col class="col-12 col-md-9">
                <router-view :profile="this.profile"></router-view>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import userService from 'service/UserService'

    export default {
        data() {
            return {
                profile: {},
            }
        },
        methods: {
            getCurrentSlug() {
                return this.$route.params.slug
            },
        },
        computed: {
            isCurrent() {
                return this.profile.id === this.$store.state.profile.profile.id
            }
        },
        beforeMount() {
            const current = this.$store.getters['profile/profile'],
                slug = this.getCurrentSlug()

            if (current && slug === current.slug) {
                this.profile = current
            } else {
                let that = this

                userService.getBySlug(slug).then((response) => {
                    that.profile = response.data
                })
            }
        }
    }
</script>
