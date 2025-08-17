package com.example.bugTracker.controller;

import com.example.bugTracker.model.Comment;
import com.example.bugTracker.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/bug/{bugId}")
    public List<Comment> getCommentsByBug(@PathVariable Long bugId) {
        return commentService.getCommentsByBugId(bugId);
    }

    @PostMapping("/bug/{bugId}")
    public Comment addComment(@PathVariable Long bugId, @RequestBody Map<String, String> request) {
        Long authorId = Long.parseLong(request.get("authorId"));
        String body = request.get("body");
        return commentService.addComment(bugId, authorId, body);
    }
}
