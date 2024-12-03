<template>
    <v-app-bar app color="#800203" elevate-on-scroll>
        <v-toolbar-title>Blep Blip Blop</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn text to="/">Home</v-btn>
        <v-btn v-if="authToken" text to="/calendar">Calendar</v-btn>
        <v-btn v-if="authToken" text to="/createGoal">Create Goal</v-btn>
        <v-btn v-if="!authToken" text to="/login">Login</v-btn>
        <v-btn v-if="!authToken" text to="/register">Register</v-btn>
        <v-btn v-if="authToken" text @click="logout">Logout</v-btn>
    </v-app-bar>
</template>

<script>
import Cookies from 'js-cookie';

export default {
    data() {
        return {
            authToken: false,
        };
    },
    methods: {
        loadAuthTokenFromCookie() {
            const authToken = Cookies.get('authToken');

            if (!authToken) {
                console.error('Token not found in cookies');
                this.authToken = null;
                this.$router.push("/");
                return;
            }

            console.log('Auth token from cookies:', authToken);
            this.authToken = true;
        },
        async logout() {
            try {
                const response = await fetch('http://localhost:8080/user/logout', {
                    method: 'POST',
                    credentials: 'include',
                });

                if (response.ok) {
                    alert('Logged out successfully!');
                    this.$router.push("/");
                    setTimeout(() => {
                        window.location.reload();
                    }, 100);
                } else {
                    console.error('Logout failed');
                }
            } catch (error) {
                console.error('Error logging out:', error);
            }
        },
    },
    mounted() {
        this.loadAuthTokenFromCookie();
        if (this.authToken) {
            console.log('Auth token found')
        } else {
            console.error('Auth token not found');
        }
    }
};
</script>