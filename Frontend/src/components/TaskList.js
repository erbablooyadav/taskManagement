import React, { useState, useEffect } from "react";
import axios from "axios";
import TaskItem from "./TaskItem";

function TaskList() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    axios
      .get("/tasks")
      .then((res) => {
        setTasks(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  const handleUpdate = (id, status) => {
    axios
      .put(`/tasks/${id}`, { status })
      .then((res) => {
        console.log(res.data);
        // Do something after updating a task
      })
      .catch((err) => {
        console.error(err);
        // Do something when an error occurs
      });
  };

  const handleDelete = (id) => {
    axios
      .delete(`/tasks/${id}`)
      .then((res) => {
        console.log(res.data);
        // Do something after deleting a task
      })
      .catch((err) => {
        console.error(err);
        // Do something when an error occurs
      });
  };

  return (
    <ul>
      {tasks.map((task) => (
        <TaskItem
          key={task.id}
          task={task}
          onUpdate={handleUpdate}
          onDelete={handleDelete}
        />
      ))}
    </ul>
  );
}

export default TaskList;