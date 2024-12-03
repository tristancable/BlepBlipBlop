<template>
    <v-app>
        <v-main class="main">
            <v-container fluid class="d-flex align-center justify-center"
                style="height: 100%; padding: 0% !important; overflow: hidden;">
                <v-card class="elevation-12" max-width="500px" style="width: 100%;">
                    <v-card-title class="text-h5 text-center">
                        Create a Goal
                    </v-card-title>
                    <v-card-text>
                        <v-form @submit.prevent="createGoal">
                            <v-text-field v-model="goal.title" label="Title" prepend-inner-icon="mdi-text" type="text"
                                outlined dense required></v-text-field>

                            <v-textarea v-model="goal.description" label="Description"
                                prepend-inner-icon="mdi-note-text-outline" outlined dense required></v-textarea>

                            <v-text-field v-model="goal.dueDate" label="Due Date" prepend-inner-icon="mdi-calendar"
                                type="date" outlined dense required></v-text-field>

                            <v-text-field v-model.number="goal.level" label="Level" prepend-inner-icon="mdi-star"
                                type="number" :max="user.userLevel" :min="1" outlined dense required></v-text-field>
                            <div class="red--text text-caption" v-if="goal.level > user.userLevel">
                                Level cannot exceed your current level!
                            </div>
                            <div class="red--text text-caption" v-else-if="goal.level < 1">
                                Level cannot be below one!
                            </div>

                            <v-select v-model="goal.priority" :items="priorityOptions" label="Priority"
                                prepend-inner-icon="mdi-format-list-numbered" outlined dense required></v-select>

                            <div class="red--text">{{ errorMessage }}</div>

                            <v-btn type="submit" color="#800203" class="mt-4" block>
                                Create Goal
                            </v-btn>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import axios from "axios";
import Cookies from 'js-cookie';

export default {
    name: 'CreateGoal',
    data() {
        return {
            user: {
                username: '',
                userLevel: null,
            },
            goal: {
                title: '',
                description: '',
                dueDate: '',
                level: 1,
                priority: 1,
            },
            priorityOptions: [1, 2, 3, 4, 5],
            errorMessage: '',
        };
    },
    methods: {
        async createGoal() {
            this.errorMessage = '';

            const goalDate = new Date(this.goal.dueDate);
            console.log('Corrected Local Goal Date:', goalDate);


            if (isNaN(goalDate.getTime())) {
                this.errorMessage = "Invalid due date.";
                return;
            }

            const formattedDate = goalDate.toISOString().slice(0, 10);
            this.goal.dueDate = formattedDate;

            if (!this.goal.title.trim() || !this.goal.description.trim()) {
                this.errorMessage = "All fields are required.";
                return;
            }

            const payload = {
                username: this.user.username,
                goal: this.goal
            };

            try {
                const response = await axios.post(`http://localhost:8080/goal`, payload, {
                    headers: { "Content-Type": "application/json" },
                });
                console.log(response.data);
                alert("Goal created successfully!");
                this.$router.push("/calendar");
            } catch (error) {
                console.error("Error creating goal:", error.response || error);
                this.errorMessage = error.response?.data || "Failed to create goal.";
            }
        },
        loadUserFromCookie() {
            const level = Cookies.get('userLevel');
            const name = Cookies.get('username');

            if (!level || !name) {
                console.error('User not found in cookies');
                this.user.userLevel = null;
                this.user.username = '';
                return;
            }

            console.log('User level from cookies:', level);
            console.log('Username from cookies:', name);
            this.user.userLevel = level;
            this.user.username = name;
        },
    },
    mounted() {
        this.loadUserFromCookie();
        console.log("Test:", Cookies.get('userLevel'), Cookies.get('username'));
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

.red--text {
    color: red;
}
</style>
