<template>
    <v-app-bar app color="primary" elevate-on-scroll>
        <v-toolbar-title>Blep Blip Blop</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn text to="/">Home</v-btn>
        <v-btn text to="/calendar">Calendar</v-btn>
        <v-btn v-if="!isLoggedIn" text to="/login">Login</v-btn>
        <v-btn v-if="!isLoggedIn" text to="/register">Register</v-btn>
        <v-btn v-if="isLoggedIn" text to="/logout" @click="logout">Logout</v-btn>
    </v-app-bar>
</template>

<script>
export default {
    data() {
        return {
            isLoggedIn: false,
        };
    },
    created() {
        // Check if the user is logged in when the component is created
        this.isLoggedIn = localStorage.getItem('loggedIn') === 'true';
    },
    methods: {
        goToLogin() {
            this.$router.push("/login");
        },
        goToRegister() {
            this.$router.push("/register");
        },
        logout() {
            localStorage.removeItem('loggedIn');
            this.isLoggedIn = false;
            this.$router.push("/login");
        }
    }
};
</script>