import axiosInstance from './path/to/your/axiosInstance';

export default {
  name: 'YourComponent',
  data() {
    return {
      data: null,
    };
  },
  methods: {
    fetchData() {
      axiosInstance.get('/endpoint') // Uses the baseURL defined in axiosInstance
        .then(response => {
          this.data = response.data;
        })
        .catch(error => {
          console.error('There was an error!', error);
        });
    },
  },
  mounted() {
    this.fetchData();
  },
};
