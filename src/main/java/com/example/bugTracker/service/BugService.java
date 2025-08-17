package com.example.bugTracker.service;

import com.example.bugTracker.model.Bug;
import com.example.bugTracker.model.Severity;
import com.example.bugTracker.model.Status;
import com.example.bugTracker.repository.BugRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BugService {
    private final BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    public List<Bug> filterBugs(Severity severity, Status status) {
        if (severity != null && status != null) {
            return bugRepository.findBySeverityAndStatus(severity,status);
        } else if (severity != null) {
            return bugRepository.findBySeverity(severity);
        } else if (status != null) {
            return bugRepository.findByStatus(status);
        }
        return bugRepository.findAll();
    }

    public Bug createBug(Bug bug) {
        bug.setCreatedAt(LocalDateTime.now());
        return bugRepository.save(bug);
    }

    public Bug updateBug(Long id, Bug bugDetails) {
        Bug bug = bugRepository.findById(id).orElseThrow();
        bug.setTitle(bugDetails.getTitle());
        bug.setDescription(bugDetails.getDescription());
        bug.setStatus(bugDetails.getStatus());
        bug.setSeverity(bugDetails.getSeverity());
        bug.setReporter(bugDetails.getReporter());
        bug.setAssignee(bugDetails.getAssignee());
        return bugRepository.save(bug);
    }

    public Bug changeStatus(Long id, Status status) {
        Bug bug = bugRepository.findById(id).orElseThrow();
        bug.setStatus(status);
        return bugRepository.save(bug);
    }

    public void deleteBug(Long id) {
        bugRepository.deleteById(id);
    }
}
