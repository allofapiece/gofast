<template>
    <div class="spot">
        <div class="spot-content">
            <div class="d-flex mb-2">
                <h3 class="display-1">{{ this.fullName }}</h3>
            </div>
            <blockquote v-if="this.profile.about">{{ this.profile.about }}</blockquote>
            <hr class="mb-2"/>
        </div>
    </div>
</template>

<script>
    import profileUtil from 'util/profile'
    import empty from 'is-empty'

    export default {
        props: {
            profile: Object
        },
        computed: {
            fullName() {
                return !empty(this.profile) ? profileUtil.fullName(this.profile) : 'User Name'
            },
        }
    }
</script>