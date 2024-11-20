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
        <div v-for="day in validMonthDays" :key="day.date ? day.date.getTime() : day.day" class="calendar-date"
          :class="{ 'today': isToday(day.date) }" @click="selectDate(day)">
          {{ day.day }}
        </div>
      </div>
    </div>
  </v-app>
</template>

<script>
export default {
  name: 'CalendarPage',
  data() {
    return {
      currentDate: new Date(),
      selectedDate: null,
      daysOfWeek: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    };
  },
  computed: {
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

      // Create the days for the current month
      for (let i = 1; i <= lastDayOfMonth.getDate(); i++) {
        daysInMonth.push({
          date: new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), i),
          day: i
        });
      }

      // Add empty days at the start of the month to align with the correct weekday
      const firstDayWeekday = firstDayOfMonth.getDay();
      const emptyDays = [];
      for (let i = 0; i < firstDayWeekday; i++) {
        emptyDays.push({ date: null, day: null });
      }

      return [...emptyDays, ...daysInMonth];
    },
    validMonthDays() {
      return this.currentMonthDays.filter(day => day.date !== null);  // Filter out days with null date
    },
  },
  methods: {
    prevMonth() {
      const prevMonth = new Date(this.currentDate);
      prevMonth.setMonth(this.currentDate.getMonth() - 1);
      this.currentDate = prevMonth;
    },
    nextMonth() {
      const nextMonth = new Date(this.currentDate);
      nextMonth.setMonth(this.currentDate.getMonth() + 1);
      this.currentDate = nextMonth;
    },
    isToday(date) {
      if (!date) return false; // Check if date is valid
      const today = new Date();
      return date.getDate() === today.getDate() &&
        date.getMonth() === today.getMonth() &&
        date.getFullYear() === today.getFullYear();
    },
    selectDate(day) {
      if (day.date) {
        this.selectedDate = day.date.toDateString();
      }
    }
  },
};
</script>

<style scoped>
/* Ensure the calendar takes up the entire screen */
html, body, #app {
  height: 100%;
  width: 100%;
  margin: 0;
}

.calendar {
  height: 100%; /* Take full screen height */
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* Ensure the header is spaced correctly */
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
  flex-grow: 1; /* Allow grid to take up available space */
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
  width: 40px; /* Fixed width for each day */
  height: 40px; /* Fixed height for each day */
  display: flex;
  justify-content: center;
  align-items: center;
}

.calendar-date.today {
  background-color: #2196f3;
  color: white;
}

.calendar-date:hover {
  background-color: #f1f1f1;
}
</style>
