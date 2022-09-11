const axios = require('axios');

const authorize = localStorage.getItem('Authorization')
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        "Content-type": "application/json",
        "Authorization": `Basic ${authorize}`
    }
});

export default axiosInstance;