import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080', // Adjust to match your backend's base URL
  timeout: 10000, // Optional: Set a timeout
  headers: {
    'Content-Type': 'application/json', // Default headers
  },
});

// Add interceptors if needed
axiosInstance.interceptors.request.use(
  config => {
    // Modify the request if needed (e.g., attach tokens)
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  response => response,
  error => {
    // Handle errors globally (optional)
    return Promise.reject(error);
  }
);

export default axiosInstance;
