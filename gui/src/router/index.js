import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Calendar from '../views/Calendar.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: { title: 'Home' },
    }, {
        path: '/calendar',
        name: 'Calendar',
        component: Calendar,
        meta: { title: 'Calendar'},
    }, {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: { title: 'Login'},
    }, {
        path: '/register',
        name: 'Register',
        component: Register,
        meta: { title: 'Register'},
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router;