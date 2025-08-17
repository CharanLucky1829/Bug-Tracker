package com.example.bugTracker.repository;

import com.example.bugTracker.model.Comment;
import com.example.bugTracker.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBug(Bug bug);
}
