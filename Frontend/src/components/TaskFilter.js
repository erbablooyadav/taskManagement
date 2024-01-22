import React, { useState } from "react";

function TaskFilter({ onFilter }) {
  const [filter, setFilter] = useState("ALL");

  const handleChange = (e) => {
    const value = e.target.value;
    setFilter(value);
    onFilter(value);
  };

  return (
    <div>
      <label htmlFor="filter">Filter by status:</label>
      <select id="filter" value={filter} onChange={handleChange}>
        <option value="ALL">All</option>
        <option value="TODO">To Do</option>
        <option value="IN_PROGRESS">In Progress</option>
        <option value="DONE">Done</option>
      </select>
    </div>
  );
}

export default TaskFilter;