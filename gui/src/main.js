import { createApp } from 'vue'
import { loadFonts } from './plugins/webfontloader'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import axiosInstance from './axios'

loadFonts()

const app = createApp(App);
app.use(router);
app.use(vuetify);
app.config.globalProperties.$axios = axiosInstance;

router.afterEach((to) => {
    document.title = to.meta.title || "Blep Blip Blop";
});

app.mount('#app');