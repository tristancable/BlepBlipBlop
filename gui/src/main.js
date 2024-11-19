import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'

loadFonts()

const app = createApp(App);
app.use(router);
app.use(vuetify);

router.afterEach((to) => {
    document.title = to.meta.title || "Blep Blip Blop";
});

app.mount('#app');