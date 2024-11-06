package com.example.TaN02.repository;

import com.example.TaN02.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.task.taskID = :taskID")
    List<Comment> getCommentByTaskId(Integer taskID);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.task.taskID = :taskID")
    Integer getCommentCount(Integer taskID);
}
