<template>
    <v-app>
        <!-- Main Content -->
        <v-main>
            <v-container fluid class="d-flex align-center justify-center" style="height: 100vh;">
                <!-- Register Card -->
                <v-card class="elevation-12" max-width="400px" style="width: 100%;">
                    <v-card-title class="text-h5 text-center">
                        Register
                    </v-card-title>
                    <v-card-text>
                        <v-form ref="form" @submit.prevent="register">
                            <!-- Username Field -->
                            <v-text-field v-model="username" label="Username" prepend-inner-icon="mdi-account"
                                type="text" outlined dense required></v-text-field>

                            <!-- Password Field -->
                            <v-text-field v-model="password" label="Password" prepend-inner-icon="mdi-lock"
                                type="password" outlined dense required></v-text-field>

                            <!-- Confirm Password Field -->
                            <v-text-field v-model="confirmPassword" label="Confirm Password"
                                prepend-inner-icon="mdi-lock" type="password" outlined dense required></v-text-field>

                            <!-- Error Message -->
                            <div class="red--text">{{ errorMessage }}</div>

                            <!-- Register Button -->
                            <v-btn type="submit" color="primary" class="mt-4" block>
                                Register
                            </v-btn>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-container>
        </v-main>
    </v-app>
</template>









<!-- <template>
    <v-app>
      <v-main>
        <v-container fluid fill-height>
          <v-row justify="center" align="center">
            <v-col cols="12" sm="8" md="4">
              <v-card class="elevation-12">
                <v-toolbar dark>
                  <v-toolbar-title>
                    {{ isRegister ? stateObj.register.name : stateObj.login.name }} Form
                  </v-toolbar-title>
                </v-toolbar>
                <v-card-text>
                  <form ref="form" @submit.prevent="isRegister ? register() : login()">
                    <v-text-field
                      v-model="username"
                      name="username"
                      label="Username"
                      type="text"
                      placeholder="username"
                      required
                    ></v-text-field>
  
                    <v-text-field
                      v-model="password"
                      name="password"
                      label="Password"
                      type="password"
                      placeholder="password"
                      required
                    ></v-text-field>
  
                    <v-text-field
                      v-if="isRegister"
                      v-model="confirmPassword"
                      name="confirmPassword"
                      label="Confirm Password"
                      type="password"
                      placeholder="confirm password"
                      required
                    ></v-text-field>
  
                    <div class="red--text">{{ errorMessage }}</div>
  
                    <v-btn type="submit" class="mt-4" color="primary" value="log in">
                      {{ isRegister ? stateObj.register.name : stateObj.login.name }}
                    </v-btn>
  
                    <div class="grey--text mt-4" @click="isRegister = !isRegister">
                      {{ toggleMessage }}
                    </div>
                  </form>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template> -->

<script>
import axios from 'axios';

export default {
    name: "RegisterPage",
    data() {
        return {
            username: "",
            password: "",
            confirmPassword: "",
            errorMessage: "",
        };
    },
    methods: {
        async register() {
            // console.log("Current Username before validation:", this.username);

            if (!this.username || this.username.trim() === "") {
                this.errorMessage = "Please input a username.";
                return;
            }

            if (this.password === this.confirmPassword) {
                this.errorMessage = "";
                try {
                    await this.registerUser();
                    this.$refs.form.reset();
                    console.log("User successfully validated:", this.username);
                } catch (error) {
                    console.error(error);
                    this.errorMessage = "An error occured. Please try again.";
                }
            } else {
                this.errorMessage = "Passwords do not match.";
            }
        },
        registerUser() {
            try {
                console.log("Registering user:", this.username);
                const response = await axios.post('http://localhost:8080/api/user', {
                    username: this.username,
                    password: this.password
                });

                console.log("Registration successful:", response.data);
                alert("Registration successful! Please log in.");
            } catch (error) {
                console.error("Error during registration:", error.response || error);
                this.errorMessage = error.response?.data?.message || "Registration failed. Please try again.";
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