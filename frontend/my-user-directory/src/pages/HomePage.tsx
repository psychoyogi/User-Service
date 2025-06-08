import { useUsers } from "../hooks/useUsers";
import { SearchBar } from "../components/SearchBar";
import { UserGrid } from "../components/UserGrid";

export const HomePage = () => {
    const { users, loading, error, fetchUsers } = useUsers();

    return (
        <div>
            <h1
                style={{
                    textAlign: "center",
                    marginBottom: "20px",
                    color: "#333",
                    fontSize: "2rem",
                    fontWeight: "bold",
                    textTransform: "uppercase",
                    letterSpacing: "1px",
                    backgroundColor: "#f8f9fa",
                    padding: "10px",
                    borderRadius: "8px",
                    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
                }}
            >
                User Directory
            </h1>
            <SearchBar onSearch={fetchUsers} />
            {loading && <p>Loading...</p>}
            {error && <p>{error}</p>}
            <UserGrid users={users} />
        </div>
    );
};
