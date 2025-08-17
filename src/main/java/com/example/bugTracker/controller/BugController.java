package com.example.bugTracker.controller;

import com.example.bugTracker.model.Bug;
import com.example.bugTracker.model.Severity;
import com.example.bugTracker.model.Status;
import com.example.bugTracker.model.User;
import com.example.bugTracker.service.BugService;
import com.example.bugTracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
@CrossOrigin(origins = "http://localhost:5173")
public class BugController {

    private final BugService bugService;
    private final UserService userService;

    public BugController(BugService bugService, UserService userService) {
        this.bugService = bugService;
        this.userService = userService;
    }

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }

    @GetMapping("/filter")
    public List<Bug> filterBugs(
            @RequestParam(required = false) Severity severity,
            @RequestParam(required = false) Status status
    ) {
        return bugService.filterBugs(severity, status);
    }

    @PostMapping
    public Bug createBug(@RequestBody BugRequest bugRequest) {
        Bug bug = new Bug();
        bug.setTitle(bugRequest.getTitle());
        bug.setDescription(bugRequest.getDescription());
        bug.setSeverity(bugRequest.getSeverity());
        bug.setStatus(bugRequest.getStatus());

        //  Fetch reporter & assignee by ID
        if (bugRequest.getReporterId() != null) {
            User reporter = userService.findById(bugRequest.getReporterId());
            bug.setReporter(reporter);
        }
        if (bugRequest.getAssigneeId() != null) {
            User assignee = userService.findById(bugRequest.getAssigneeId());
            bug.setAssignee(assignee);
        }

        return bugService.createBug(bug);
    }

    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable Long id, @RequestBody BugRequest bugRequest) {
        Bug bug = new Bug();
        bug.setId(id);
        bug.setTitle(bugRequest.getTitle());
        bug.setDescription(bugRequest.getDescription());
        bug.setSeverity(bugRequest.getSeverity());
        bug.setStatus(bugRequest.getStatus());

        if (bugRequest.getReporterId() != null) {
            User reporter = userService.findById(bugRequest.getReporterId());
            bug.setReporter(reporter);
        }
        if (bugRequest.getAssigneeId() != null) {
            User assignee = userService.findById(bugRequest.getAssigneeId());
            bug.setAssignee(assignee);
        }

        return bugService.updateBug(id, bug);
    }

    @PatchMapping("/{id}/status")
    public Bug changeStatus(@PathVariable Long id, @RequestParam Status status) {
        return bugService.changeStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
    }

    //  DTO for request
    public static class BugRequest {
        private String title;
        private String description;
        private Severity severity;
        private Status status;
        private Long reporterId;
        private Long assigneeId;

        // getters & setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public Severity getSeverity() { return severity; }
        public void setSeverity(Severity severity) { this.severity = severity; }

        public Status getStatus() { return status; }
        public void setStatus(Status status) { this.status = status; }

        public Long getReporterId() { return reporterId; }
        public void setReporterId(Long reporterId) { this.reporterId = reporterId; }

        public Long getAssigneeId() { return assigneeId; }
        public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
    }
}
