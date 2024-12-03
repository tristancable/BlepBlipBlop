<template>
    <v-app>
        <v-main>
            <v-container fluid class="d-flex align-center justify-center" style="height: 100vh;">
                <v-card class="elevation-12" max-width="400px" style="width: 100%;">
                    <v-card-title class="text-h5 text-center">
                        Register
                    </v-card-title>
                    <v-card-text>
                        <v-form ref="form" @submit.prevent="register">
                            <v-text-field v-model="username" label="Username" prepend-inner-icon="mdi-account"
                                type="text" outlined dense required></v-text-field>

                            <v-text-field v-model="level" label="Level" prepend-inner-icon="mdi-pound" type="text"
                                outlined dense required></v-text-field>

                            <v-text-field v-model="password" label="Password" prepend-inner-icon="mdi-lock"
                                type="password" outlined dense required></v-text-field>

                            <v-text-field v-model="confirmPassword" label="Confirm Password"
                                prepend-inner-icon="mdi-lock" type="password" outlined dense required></v-text-field>

                            <div class="red--text">{{ errorMessage }}</div>

                            <v-btn type="submit" color="#800203" class="mt-4" block>
                                Register
                            </v-btn>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import axios from 'axios';

export default {
    name: "RegisterPage",
    data() {
        return {
            username: "",
            password: "",
            confirmPassword: "",
            level: "",
            errorMessage: "",
        };
    },
    methods: {
        async register() {
            if (!this.username || this.username.trim() === "") {
                this.errorMessage = "Please input a username.";
                return;
            }

            if (!this.level || this.level.trim() === "") {
                this.errorMessage = "Please input a level.";
                return;
            } else if (this.level > 4 && this.level < 1) {
                this.errorMessage = "Level must be between 1 and 4."
                return;
            }

            if (!this.password || !this.confirmPassword) {
                this.errorMessage = "Please input a password.";
                return;
            }

            if (this.password !== this.confirmPassword) {
                this.errorMessage = "Passwords do not match.";
                return;
            }

            this.errorMessage = "";

            try {
                await this.registerUser();
                this.$refs.form.reset();
                console.log("User successfully validated:", this.username);
            } catch (error) {
                console.error(error);
                this.errorMessage = "An error occurred. Please try again.";
            }
        },
        async registerUser() {
            try {
                console.log("Registering user:", this.username);
                const response = await axios.post('http://localhost:8080/user', {
                    username: this.username,
                    password: this.password,
                    level: this.level,
                })

                if (response.data === "User created") {
                    console.log("Registration successful:", response.data);
                    alert("Registration successful! Please log in.");
                    this.$router.push('/login');
                } else {
                    this.errorMessage = "Registration failed. Please try again.";
                }
            } catch (error) {
                console.error("Error during registration:", error.response || error);
                if (error.response?.data === "Username taken") {
                    this.errorMessage = "Username already taken.";
                } else {
                    this.errorMessage = error.response?.data || "Registration failed. Please try again.";
                }
            }
        },
    },
};
</script>

<style scoped>
.v-container {
    height: 90vh;
    padding: 0;
}

.v-card {
    width: 100%;
    padding: 20px;
}

.red--text {
    color: red;
}
</style>