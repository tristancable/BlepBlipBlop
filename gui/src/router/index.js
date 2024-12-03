import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Calendar from '../views/Calendar.vue'
import CreateGoal from '../views/CreateGoal.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Logout from '../views/LogoutView.vue'
import Cookies from 'js-cookie';

function isAuthenticated() {
    return !!Cookies.get('authToken');
}

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
        meta: { title: 'Calendar' },
    }, {
        path: '/createGoal',
        name: 'Create Goal',
        component: CreateGoal,
        meta: { title: 'Create Goal' },
    }, {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: { title: 'Login', requiresNoAuth: true },
    }, {
        path: '/register',
        name: 'Register',
        component: Register,
        meta: { title: 'Register', requiresNoAuth: true },
    }, {
        path: '/logout',
        name: 'Logout',
        component: Logout,
        meta: { title: 'Logout', requiresNoAuth: true },
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

router.beforeEach((to, _from, next) => {
    const loggedIn = isAuthenticated();
    console.log(`Logged in: ${loggedIn}, Navigating to: ${to.name}`);

    if (to.meta.requiresNoAuth && loggedIn) {
        console.log('Redirecting to /');
        return next('/');
    }

    next();
});

export default router;