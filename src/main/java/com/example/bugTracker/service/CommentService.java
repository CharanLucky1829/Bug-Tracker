package com.example.bugTracker.service;

import com.example.bugTracker.model.Bug;
import com.example.bugTracker.model.Comment;
import com.example.bugTracker.model.User;
import com.example.bugTracker.repository.BugRepository;
import com.example.bugTracker.repository.CommentRepository;
import com.example.bugTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BugRepository bugRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, BugRepository bugRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.bugRepository = bugRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getCommentsByBugId(Long bugId) {
        Bug bug = bugRepository.findById(bugId).orElseThrow();
        return commentRepository.findByBug(bug);
    }

    public Comment addComment(Long bugId, Long authorId, String body) {
        Bug bug = bugRepository.findById(bugId).orElseThrow();
        User author = userRepository.findById(authorId).orElseThrow();
        Comment comment = new Comment(bug, author, body);
        return commentRepository.save(comment);
    }
}
