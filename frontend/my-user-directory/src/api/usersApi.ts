import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
});

export const searchUsers = (query: string) => api.get(`/users/search?query=${query}`);

export const getUserById = (id: number) => api.get(`/users/${id}`);

export const getUserByEmail = (email: string) => api.get(`/users/email/${email}`);
