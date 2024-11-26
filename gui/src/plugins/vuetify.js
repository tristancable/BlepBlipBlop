// Styles
import '@mdi/font/css/materialdesignicons.css'; // For Material Design Icons
import 'vuetify/styles'; // Vuetify core styles
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
// import { aliases, mdi } from 'vuetify/iconsets/mdi';

// Vuetify
import { createVuetify } from 'vuetify';

// Create Vuetify instance
export default createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'light',
  },
  // icons: {
  //   defaultSet: 'mdi',
  //   aliases,
  //   sets: {
  //     mdi,
  //   },
  // },
  // theme: {
  //   defaultTheme: 'light', // Or dark
  // },
});