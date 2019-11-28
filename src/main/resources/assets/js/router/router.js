import Vue from 'vue'
import VueRouter from 'vue-router'
import ProfileHome from 'vue/components/profile/ProfileHome.vue'
import ProfileGeneral from 'vue/components/profile/General.vue'
// User Edit
import Edit from 'vue/components/profile/edit/Edit.vue'
import EditGeneral from 'vue/components/profile/edit/general/General.vue'
// User Settings
import General from 'vue/components/profile/setting/General.vue'
import Settings from 'vue/components/profile/setting/Settings.vue'
import Security from 'vue/components/profile/setting/security/Security.vue'

import Points from 'vue/components/points/Points.vue'

import Home from "vue/pages/Home.vue";
import SignIn from "vue/components/auth/SignIn.vue";
import SignUp from "vue/components/auth/SignUp.vue";
import Feed from "vue/components/feed/Feed.vue";

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Home},
    {path: '/signin', component: SignIn},
    {path: '/signup', component: SignUp},
    {path: '/feed', component: Feed},
    {
        path: '/user/settings', component: Settings,
        children: [
            {
                path: 'general',
                component: General
            },
            {
                path: 'security',
                component: Security
            },
        ]
    },
    {
        path: '/user/edit', component: Edit,
        children: [
            {
                path: 'general',
                component: EditGeneral
            }
        ]
    },
    {
        path: '/points',
        component: Points
    },
    {path: '/:slug?', component: ProfileHome, children: [
            {
                path: 'general',
                component: ProfileGeneral
            },
        ]},

    {path: '*', component: Home}
]

export default new VueRouter({
    mode: 'history',
    routes
})
