<template>
  <div class="container">
    <div class="card">
      <h2>Home Page</h2>
      <p>Welcome to the Umbrella Corporation Goal Planner!</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage'
}
</script>

<style scoped>
/* Centering the container */
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* Full height of the viewport */
}

/* Card styling */
.card {
  background-color: black; /* White background for the card */
  border-radius: 10px; /* Rounded corners */
  padding: 20px; /* Space inside the card */
  width: 400px; /* Fixed width for the card */
  box-shadow: 0 4px 8px rgba(255, 0, 0, 0.1); /* Subtle shadow */
  text-align: center; /* Center text inside the card */
}

/* Card title */
.card h2 {
  color: white; /* Dark text color for the title */
  margin-bottom: 10px;
}

/* Paragraph text */
.card p {
  color: white; /* Lighter color for the text */
}
</style>
