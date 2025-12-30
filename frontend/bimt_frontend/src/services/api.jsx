import axios from "axios";

// Axios instance with backend base URL
const api = axios.create({
  baseURL: "http://localhost:8080", // Spring Boot backend
});

export default api;
