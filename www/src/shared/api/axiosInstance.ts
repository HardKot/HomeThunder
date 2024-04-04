"use client"
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: `https://api.localhost`,
  headers: {
    "Content-Type": "application/json"
  }
})

axiosInstance.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (response) => {

    return response.data;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
