package com.example.bugTracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status; // OPEN, IN_PROGRESS, CLOSED
    @Enumerated(EnumType.STRING)
    private Severity severity; // LOW, MEDIUM, HIGH

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private User reporter;
    @ManyToOne
    @JoinColumn(name="assignee_id")
    private User assignee;

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Bug() {}

    public Bug(String title, String description, Status status, Severity severity, User reportedBy, User assignedTo) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.severity = severity;
        this.reporter = reportedBy;
        this.assignee = assignedTo;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
