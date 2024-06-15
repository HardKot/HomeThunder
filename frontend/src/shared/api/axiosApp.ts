import axios from "axios";

axios.defaults.baseURL = process.env.BACKEND_URL ?? "http://localhost:8080";
