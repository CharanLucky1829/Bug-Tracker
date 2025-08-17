import React from 'react';

export default function BugList({ bugs, onDelete }) {
  if (!bugs.length) return <p>No bugs yet!</p>;

  return (
    <table className="table table-bordered mt-3">
      <thead>
        <tr>
          <th>Title</th>
          <th>Severity</th>
          <th>Status</th>
          <th>Reporter</th>
          <th>Assignee</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {bugs.map(bug => (
          <tr key={bug.id}>
            <td>{bug.title}</td>
            <td>{bug.severity}</td>
            <td>{bug.status}</td>
            <td>{bug.reporter?.name}</td>
            <td>{bug.assignee?.name}</td>
            <td>
              <button className="btn btn-sm btn-danger" onClick={() => onDelete(bug.id)}>Delete</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
