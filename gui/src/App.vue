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
    <Navbar />
    <router-view></router-view>
  </v-app>
</template>










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
