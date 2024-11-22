<script>
import Navbar from '@/components/NavBar.vue'

export default {
  name: 'App',
  components: {
    Navbar
  }
};
</script>

<template>
  <v-app>
    <v-main>
      <Navbar />
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<style scoped>
body,
html {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
}

.v-application {
  height: 100%;
}

.v-main {
  height: 100%;
  /* Ensure v-main takes full height */
  padding: 0;
  margin: 0;
}

.v-container {
  height: 100%;
  padding: 0;
}
</style>








<!-- <template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>Blep Blip Blop</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text>Home</v-btn>
      <v-btn text to="/calendar">Calendar</v-btn>
    </v-app-bar>

    <v-main>
      <v-container>
        <h1>Welcome to the Home Page!</h1>
        <p>This is your main content.</p>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
/* You can customize the style of the app bar here */
</style> -->
