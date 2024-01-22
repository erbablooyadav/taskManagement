import React, { useState } from "react";

function TaskItem({ task, onUpdate, onDelete }) {
  const [status, setStatus] = useState(task.status);

  const handleChange = (e) => {
    const value = e.target.value;
    setStatus(value);
    onUpdate(task.id, value);
  };

  const handleDelete = () => {
    onDelete(task.id);
  };

  return (
    <li>
      <h3>{task.title}</h3>
      <p>{task.description}</p>
      <select value={status} onChange={handleChange}>
        <option value="TODO">To Do</option>
        <option value="IN_PROGRESS">In Progress</option>
        <option value="DONE">Done</option>
      </select>
      <button onClick={handleDelete}>Delete</button>
    </li>
  );
}

export default TaskItem;