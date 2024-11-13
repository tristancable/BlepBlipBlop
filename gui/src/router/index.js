import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
// import AboutPage from '../components/AboutPage.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomePage
    }
    //   },
    //   {
    //     path: '/about',
    //     name: 'About',
    //     component: AboutPage
    //   }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
