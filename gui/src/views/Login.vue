<template>
    <v-app>
        <!-- Main Content -->
        <v-main class="main">
            <v-container fluid class="d-flex align-center justify-center"
                style="height: 90%; padding: 0% !important; overflow: hidden;">
                <!-- Login Card -->
                <v-card class="elevation-12" max-width="400px" style="width: 100%;">
                    <v-card-title class="text-h5 text-center">
                        Login
                    </v-card-title>
                    <v-card-text>
                        <v-form @submit.prevent="loginUser">
                            <!-- Username Field -->
                            <v-text-field v-model="username" label="Username" prepend-inner-icon="mdi-account"
                                type="text" outlined dense required></v-text-field>

                            <!-- Password Field -->
                            <v-text-field v-model="password" label="Password" prepend-inner-icon="mdi-lock"
                                type="password" outlined dense required></v-text-field>

                            <!-- Login Button -->
                            <v-btn color="primary" class="mt-4" block @click="loginUser">
                                Login
                            </v-btn>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
export default {
    name: 'LoginPage',
    data() {
        return {
            username: '',
            password: ''
        };
    },
    methods: {
        loginUser() {
            console.log("Logging in", this.username);
            // Handle login logic here
        }
    }
};
</script>

<style scoped>
.v-main {
    padding: 0%;
}

.v-card {
    padding: 20px;
}

.v-container {
    height: 90vh;
    padding: 0%;
}
</style>