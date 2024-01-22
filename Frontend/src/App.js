import React, { useState } from "react";
import axios from "axios";
import TaskForm from "./components/TaskForm";
import TaskList from "./components/TaskList";
import TaskFilter from "./components/TaskFilter";

function App() {
  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState("ALL");

  const handleCreate = (task) => {
    axios
      .post("http://localhost:8081/tasks", task)
      .then((res) => {
        console.log(res.data);
        setTasks([...tasks, task]);
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const handleUpdate = (id, status) => {
    axios
      .put(`http://localhost:8081/tasks/${id}`, { status })
      .then((res) => {
        console.log(res.data);
        setTasks(
          tasks.map((task) =>
            task.id === id ? { ...task, status } : task
          )
        );
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:8081/tasks/${id}`)
      .then((res) => {
        console.log(res.data);
        setTasks(tasks.filter((task) => task.id !== id));
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const handleFilter = (filter) => {
    setFilter(filter);
    if (filter === "ALL") {
      axios
        .get("http://localhost:8081/tasks")
        .then((res) => {
          setTasks(res.data);
        })
        .catch((err) => {
          console.error(err);
        });
    } else {
      axios
        .get(`http://localhost:8081/tasks/search?status=${filter}`)
        .then((res) => {
          setTasks(res.data);
        })
        .catch((err) => {
          console.error(err);
        });
    }
  };

  return (
    <div className="App">
      <h1>Task Management Application</h1>
      <TaskForm onCreate={handleCreate} />
      <TaskFilter onFilter={handleFilter} />
      <TaskList tasks={tasks} onUpdate={handleUpdate} onDelete={handleDelete} />
    </div>
  );
}

export default App;