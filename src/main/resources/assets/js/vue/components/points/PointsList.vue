<template>
    <div>
        <Point v-for="point in this.points" :point="point" :key="point.id"></Point>
    </div>
</template>

<script>
    import Point from './Point.vue'

    export default {
        components: {
            Point
        },
        props: {
            points: Array
        },
    }
</script>
