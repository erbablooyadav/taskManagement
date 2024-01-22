import React, { useState, useRef } from "react";
import axios from "axios";

function TaskForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("TODO");
  const titleRef = useRef();

  const handleChange = (e) => {
    const { name, value } = e.target;
    switch (name) {
      case "title":
        setTitle(value);
        break;
      case "description":
        setDescription(value);
        break;
      case "status":
        setStatus(value);
        break;
      default:
        break;
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const task = { title, description, status };
    axios
      .post("/tasks", task)
      .then((res) => {
        console.log(res.data);
        // Do something after creating a new task
      })
      .catch((err) => {
        console.error(err);
        // Do something when an error occurs
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="title">Title</label>
      <input
        type="text"
        id="title"
        name="title"
        value={title}
        onChange={handleChange}
        ref={titleRef}
        required
      />
      <label htmlFor="description">Description</label>
      <textarea
        id="description"
        name="description"
        value={description}
        onChange={handleChange}
      />
      <label htmlFor="status">Status</label>
      <select id="status" name="status" value={status} onChange={handleChange}>
        <option value="TODO">To Do</option>
        <option value="IN_PROGRESS">In Progress</option>
        <option value="DONE">Done</option>
      </select>
      <button type="submit">Create Task</button>
    </form>
  );
}

export default TaskForm;