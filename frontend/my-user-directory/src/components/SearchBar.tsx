import { useState } from "react";

interface Props {
    onSearch: (query: string) => void;
}

export const SearchBar: React.FC<Props> = ({ onSearch }) => {
    const [query, setQuery] = useState("");

    const handleSearch = () => {
        if (query.length >= 3) {
            onSearch(query);
        }
    };

    return (
        <div>
            <input
                type="text"
                placeholder="Search users..."
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                onKeyDown={(e) => e.key === "Enter" && handleSearch()}
                style={{
                    padding: "8px",
                    borderRadius: "4px",
                    border: "1px solid #ccc",
                    width: "300px",
                    marginRight: "8px",
                }}
            />
            <button
                style={{
                    backgroundColor: "#007bff",
                    color: "white",
                    border: "none",
                    padding: "8px 16px",
                    cursor: "pointer",
                    borderRadius: "4px",
                }}
                onClick={handleSearch}
            >
                Search
            </button>
        </div>
    );
};
