import { useState } from "react";
import type { User } from "../types/User";
import styles from "./UserGrid.module.css";

interface Props {
    users: User[];
}

type SortDirection = "asc" | "desc" | null;

export const UserGrid: React.FC<Props> = ({ users }) => {
    const [sortDirection, setSortDirection] = useState<SortDirection>(null);
    const [selectedRole, setSelectedRole] = useState<string>("all");

    const uniqueRoles = Array.from(new Set(users.map((user) => user.role)));

    const sortedUsers = [...users].sort((a, b) => {
        if (!sortDirection) return 0;
        return sortDirection === "asc" ? a.age - b.age : b.age - a.age;
    });

    const filteredUsers = sortedUsers.filter((user) => selectedRole === "all" || user.role === selectedRole);

    const handleSort = () => {
        const newDirection: SortDirection = sortDirection === null ? "asc" : sortDirection === "asc" ? "desc" : null;
        setSortDirection(newDirection);
    };

    return (
        <div className={styles.container}>
            <div className={styles.header}>
                <select className={styles.select} value={selectedRole} onChange={(e) => setSelectedRole(e.target.value)}>
                    <option value="all">All Roles</option>
                    {uniqueRoles.map((role) => (
                        <option key={role} value={role}>
                            {role}
                        </option>
                    ))}
                </select>
                <div className={styles.stats}>
                    Showing {filteredUsers.length} of {users.length} users
                </div>
            </div>

            <table className={styles.table}>
                <thead>
                    <tr>
                        <th className={styles.th}>Name</th>
                        <th className={styles.th}>Email</th>
                        <th className={styles.th}>
                            Age
                            <button className={styles.sortButton} onClick={handleSort} title="Sort by age">
                                {sortDirection === "asc" ? "↑" : sortDirection === "desc" ? "↓" : "≡"}
                            </button>
                        </th>
                        <th className={styles.th}>SSN</th>
                        <th className={styles.th}>Role</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredUsers.length > 0 ? (
                        filteredUsers.map((user) => (
                            <tr key={user.id} className={styles.row}>
                                <td className={styles.td}>
                                    {user.first_name} {user.last_name}
                                </td>
                                <td className={styles.td}>{user.email}</td>
                                <td className={styles.td}>{user.age}</td>
                                <td className={styles.td}>{user.ssn}</td>
                                <td className={styles.td}>
                                    <span className={styles.badge}>{user.role}</span>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan={5} className={styles.emptyState}>
                                No users found
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};
