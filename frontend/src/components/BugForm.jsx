import React, { useState ,useEffect} from 'react';

export default function BugForm({ onSave, users }) {
  const [bug, setBug] = useState({
    title: '',
    description: '',
    severity: 'LOW',
    status: 'OPEN',
    reporterId: '',
    assigneeId: ''
  });

   useEffect(() => {
    fetch("http://localhost:8080/api/users")
      .then(res => res.json())
      .then(data => setUsers(data))
      .catch(err => console.error("Error fetching users:", err));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBug(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const updatedBug = {
      title: bug.title,
      description: bug.description,
      severity: bug.severity,
      status: bug.status,
      reporterId: bug.reporterId ? parseInt(bug.reporterId) : null,
      assigneeId: bug.assigneeId ? parseInt(bug.assigneeId) : null,
    };

    onSave(updatedBug);

    // reset form
    setBug({
      title: '',
      description: '',
      severity: 'LOW',
      status: 'OPEN',
      reporterId: '',
      assigneeId: ''
    });
  };

  return (
    <form onSubmit={handleSubmit} className="border p-3 bg-light rounded">
      <h5>Create Bug</h5>

      <div className="row mb-2">
        <div className="col">
          <input
            className="form-control"
            name="title"
            value={bug.title}
            onChange={handleChange}
            placeholder="Title"
            required
          />
        </div>
        <div className="col">
          <select
            className="form-select"
            name="severity"
            value={bug.severity}
            onChange={handleChange}
          >
            <option value="LOW">Low</option>
            <option value="MEDIUM">Medium</option>
            <option value="HIGH">High</option>
          </select>
        </div>
        <div className="col">
          <select
            className="form-select"
            name="status"
            value={bug.status}
            onChange={handleChange}
          >
            <option value="OPEN">Open</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="CLOSED">Closed</option>
          </select>
        </div>
      </div>

      <textarea
        className="form-control mb-2"
        name="description"
        value={bug.description}
        onChange={handleChange}
        placeholder="Description"
        required
      />

      <div className="row mb-2">
        <div className="col">
          <select
            className="form-select"
            name="reporterId"
            value={bug.reporterId}
            onChange={handleChange}
            required
          >
            <option value="">Reporter</option>
            {users.map(u => (
              <option key={u.id} value={u.id}>{u.name}</option>
            ))}
          </select>
        </div>
        <div className="col">
          <select
            className="form-select"
            name="assigneeId"
            value={bug.assigneeId}
            onChange={handleChange}
            required
          >
            <option value="">Assignee</option>
            {users.map(u => (
              <option key={u.id} value={u.id}>{u.name}</option>
            ))}
          </select>
        </div>
      </div>

      <button className="btn btn-primary">Add Bug</button>
    </form>
  );
}
