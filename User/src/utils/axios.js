const axios = require('axios');

const authorize = localStorage.getItem('Authorization')
const headers = {
    "Content-type": "application/json",
    "Authorization": `Basic ${authorize}`
}

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: (!authorize || authorize === '') ? {
        "Content-type": "application/json",
    } : headers
});

export default axiosInstance;