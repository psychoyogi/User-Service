import { useState } from "react";
import { searchUsers } from "../api/usersApi";
import type { User } from "../types/User";

export const useUsers = () => {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const fetchUsers = async (query: string) => {
        setLoading(true);
        try {
            const response = await searchUsers(query);
            console.log("Fetched users:", response.data.data);
            setUsers(response.data.data);
            setError(null);
        } catch (err) {
            setError("Failed to fetch users");
            console.error("Error fetching users:", err);
        } finally {
            setLoading(false);
        }
    };

    return { users, loading, error, fetchUsers };
};
