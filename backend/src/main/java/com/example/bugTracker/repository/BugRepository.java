package com.example.bugTracker.repository;

import com.example.bugTracker.model.Bug;
import com.example.bugTracker.model.Severity;
import com.example.bugTracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {

    // Filter by priority (severity) and status
    List<Bug> findBySeverityAndStatus(Severity severity, Status status);

    // Filter only by priority
    List<Bug> findBySeverity(Severity severity);

    // Filter only by status
    List<Bug> findByStatus(Status status);
}
