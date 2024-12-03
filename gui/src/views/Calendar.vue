<!-- Created from the help of ChatGPT -->

<template>
  <v-app>
    <div class="calendar">
      <div class="calendar-header">
        <button @click="prevMonth">Previous</button>
        <h2>{{ monthName }} {{ year }}</h2>
        <button @click="nextMonth">Next</button>
      </div>
      <div class="calendar-grid">
        <div class="calendar-day" v-for="day in daysOfWeek" :key="day">{{ day }}</div>
        <div v-for="day in validMonthDays" :key="day.date ? day.date.getTime() : day.day" class="calendar-date" :class="{
          'today': isToday(day.date)
        }" @click="selectDate(day)">
          {{ day.day }}
          <div v-if="day.goalCount > 0" :class="getGoalStatusClass(day.date)" class="goal-count">
            {{ day.goalCount }} Goal{{ day.goalCount > 1 ? 's' : '' }}
          </div>
        </div>
      </div>
      <div>
        <h2>Goals for {{ selectedDate }}</h2>
        <ul>
          <li v-for="goal in goalsForSelectedDate" :key="goal.id">
            <div class="goal-container">
              <label class="goal-label">
                <input type="checkbox" :checked="goal.completed" @change="toggleGoalCompletion(goal)" />
                <span :class="{ completed: goal.completed }"></span>
                <div class="goal-info">
                  <h3 class="goal-title">{{ goal.title }} ({{ goal.level }})<button @click="deleteGoal(goal)"
                      class="delete-btn">Delete</button></h3>
                  <p class="goal-description">{{ goal.description }}</p>
                </div>
              </label>
            </div>
          </li>
        </ul>
        <p>Remaining Goals: {{ remainingGoalsCount }}</p>
        <p v-if="goalsForSelectedDate.length === 0">No goals set for this day</p>
      </div>
    </div>
  </v-app>
</template>

<script>
import axios from "axios";
import Cookies from 'js-cookie';

export default {
  name: 'CalendarPage',
  data() {
    return {
      currentDate: new Date(),
      selectedDate: null,
      daysOfWeek: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
      goalsForSelectedDate: [],
      userLevel: null,
      username: '',
    };
  },
  computed: {
    remainingGoalsCount() {
      return this.goalsForSelectedDate.filter(goal => !goal.completed).length;
    },
    monthName() {
      const months = [
        'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August',
        'September', 'October', 'November', 'December'
      ];
      return months[this.currentDate.getMonth()];
    },
    year() {
      return this.currentDate.getFullYear();
    },
    currentMonthDays() {
      const firstDayOfMonth = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), 1);
      const lastDayOfMonth = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth() + 1, 0);
      const daysInMonth = [];

      for (let i = 1; i <= lastDayOfMonth.getDate(); i++) {
        daysInMonth.push({
          date: new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), i),
          day: i,
          goals: []
        });
      }

      const firstDayWeekday = firstDayOfMonth.getDay();
      const emptyDays = [];
      for (let i = 0; i < firstDayWeekday; i++) {
        emptyDays.push({ date: null, day: null });
      }

      return [...emptyDays, ...daysInMonth];
    },
    validMonthDays() {
      return this.currentMonthDays.filter(day => day.date !== null);
    },
  },
  methods: {
    async selectDate(day) {
      if (day.date) {
        console.log("Selected date:", day.date);
        this.selectedDate = day.date.toDateString();

        try {
          const response = await axios.get(`http://localhost:8080/goal?username=${this.username}`);
          console.log("API Response:", response.data);

          const filteredGoals = response.data.filter(goal => {
            const goalDate = new Date(goal.dueDate);
            const selectedDate = new Date(day.date);
            return goalDate.toDateString() === selectedDate.toDateString();
          });

          this.goalsForSelectedDate = filteredGoals;

        } catch (error) {
          console.error('Error fetching goals:', error);
        }
      }
    },
    loadUserFromCookie() {
      const level = Cookies.get('userLevel');
      const name = Cookies.get('username');

      if (!level || !name) {
        console.error('User not found in cookies');
        this.userLevel = null;
        this.username = '';
        return;
      }

      console.log('User level from cookies:', level);
      console.log('Username from cookies:', name);
      this.userLevel = level;
      this.username = name;
    },
    displayGoals(goals, day) {
      console.log("Goals for the day:", goals);
      console.log("Day being updated:", day);
      this.goalsForSelectedDate = goals;
      day.goals = goals;
    },
    prevMonth() {
      const prevMonth = new Date(this.currentDate);
      prevMonth.setMonth(this.currentDate.getMonth() - 1);
      if (prevMonth.getMonth() === 11) {
        this.year--;
      }
      this.currentDate = prevMonth;
      this.loadGoalsForMonth();
      this.loadGoalsFromStorage();
    },
    nextMonth() {
      const nextMonth = new Date(this.currentDate);
      nextMonth.setMonth(this.currentDate.getMonth() + 1);
      this.currentDate = nextMonth;
      this.loadGoalsForMonth();
      this.loadGoalsFromStorage();
    },
    isToday(date) {
      if (!date) return false;
      const today = new Date();
      return date.getDate() === today.getDate() &&
        date.getMonth() === today.getMonth() &&
        date.getFullYear() === today.getFullYear();
    },
    isDueDate(date) {
      const today = new Date();
      return date.getDate() === today.getDate() &&
        date.getMonth() === today.getMonth() &&
        date.getFullYear() === today.getFullYear();
    },
    async loadGoalsForMonth() {
      try {
        const response = await axios.get(`http://localhost:8080/goal?username=${this.username}`);
        const goals = response.data;
        localStorage.setItem('monthlyGoals', JSON.stringify(goals));
        this.assignGoalsToDays(goals);
      } catch (error) {
        console.error('Error fetching goals:', error);
      }
    },
    assignGoalsToDays(goals) {
      this.currentMonthDays.forEach(day => {
        if (day.date) {
          const goalsForDay = goals.filter(goal => {
            const goalDate = new Date(goal.dueDate);
            const dayDate = new Date(day.date);
            return goalDate.toDateString() === dayDate.toDateString();
          });
          day.goals = goalsForDay;
          day.goalCount = goalsForDay.length;
        }
      });
    },
    loadGoalsFromStorage() {
      const storedGoals = localStorage.getItem('monthlyGoals');
      if (storedGoals) {
        const goals = JSON.parse(storedGoals);
        this.assignGoalsToDays(goals);
      }
    },
    getGoalStatusClass(dueDate) {
      if (!dueDate) return '';
      const now = new Date();
      const today = new Date(now.setHours(0, 0, 0, 0));
      const due = new Date(dueDate.setHours(0, 0, 0, 0))
      if (today.getTime() === due.getTime()) {
        return 'goal-not-overdue';
      } else if (today < due) {
        return 'goal-not-overdue';
      } else if (today > due) {
        return 'goal-overdue';
      }
      return;
    },
    async toggleGoalCompletion(goal) {
      try {
        const response = await axios.put(`http://localhost:8080/goal/${goal.id}`, { completed: !goal.completed });
        goal.completed = !goal.completed;
        this.updateGoalCount(goal);
        console.log('Goal updated successfully:', response.data);
      } catch (error) {
        console.error('Error toggling goal completion:', error.response ? error.response.data : error.message);
      }
    },
    updateGoalCount(goal) {
      const goalDueDate = new Date(goal.dueDate);
      const day = this.currentMonthDays.find(d => {
        const dayDate = new Date(d.date);
        return goalDueDate.toDateString() === dayDate.toDateString();
      });

      if (day) {
        if (goal.completed) {
          day.goalCount--;
        } else {
          day.goalCount++;
        }
      }
    },
    async deleteGoal(goal) {
      try {
        // Make an API request to delete the goal
        await axios.delete(`http://localhost:8080/goal/${goal.id}`);

        // Remove the goal from the local goals list for the selected date
        this.goalsForSelectedDate = this.goalsForSelectedDate.filter(g => g.id !== goal.id);

        // Update the goal count for the day
        this.updateGoalCountAfterDelete(goal);

        console.log('Goal deleted successfully');
      } catch (error) {
        console.error('Error deleting goal:', error);
      }
    },
    updateGoalCountAfterDelete(deletedGoal) {
      const goalDueDate = new Date(deletedGoal.dueDate);
      const day = this.currentMonthDays.find(d => {
        const dayDate = new Date(d.date);
        return goalDueDate.toDateString() === dayDate.toDateString();
      });

      if (day) {
        day.goalCount--; // Decrease the goal count for that day
      }
    },
  },
  async mounted() {
    this.loadUserFromCookie();
    await this.loadGoalsForMonth();

    const today = new Date();
    const todayDayObject = this.validMonthDays.find(day =>
      day.date && day.date.toDateString() === today.toDateString()
    );
    if (todayDayObject) {
      this.selectDate(todayDayObject);
    }
  }
};
</script>

<style scoped>
html,
body,
#app {
  height: 100%;
  width: 100%;
  margin: 0;
}

.calendar {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 10%;
}

.calendar-header {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-content: space-between;
  justify-items: center;
  width: 100%;
  margin-bottom: 10px;
}

.calendar-grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 5px;
  flex-grow: 1;
  justify-items: center;
  width: 100%;
}

.calendar-day {
  font-weight: bold;
}

.calendar-date {
  padding: 15px;
  cursor: pointer;
  border-radius: 5px;
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.calendar-date.today {
  background-color: rgba(0, 0, 0, 0.3);
  color: white;
}

.calendar-date:hover {
  background-color: #f1f1f1;
  color: black;
}

.goal-count {
  font-size: 10px;
  font-weight: bold;
}

.goal-overdue {
  color: red;
}

.goal-not-overdue {
  color: green;
}

.completed {
  text-decoration: line-through;
  color: gray;
}

.goal-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.goal-label {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  gap: 10px;
  padding: 10px;
  width: 250px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.goal-label:hover {
  background-color: #f0f0f0;
}

.goal-label input[type="checkbox"] {
  margin-top: 5px;
  transform: scale(1.2);
  cursor: pointer;
}

.goal-info {
  display: flex;
  flex-direction: column;
}

.goal-title {
  font-size: 18px;
  font-weight: bold;
  margin: 0;
  color: #333;
}

.goal-description {
  font-size: 14px;
  color: #555;
  margin: 5px 0 0;
}

.delete-btn {
  background-color: #e74c3c;
  color: white;
  font-size: 16px;
  font-weight: bold;
  padding: 2.5px 5px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
  margin-left: 5px;
}

.delete-btn:hover {
  background-color: #c0392b;
  transform: scale(1.05);
}

.delete-btn:active {
  background-color: #922b21;
}

.delete-btn:focus {
  outline: none;
}
</style>