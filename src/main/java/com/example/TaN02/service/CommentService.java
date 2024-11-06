package com.example.TaN02.service;

import com.example.TaN02.entity.Comment;
import com.example.TaN02.repository.CommentRepository;
import com.example.TaN02.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getCommentByTaskId(Integer taskID) {
        return commentRepository.getCommentByTaskId(taskID);
    }

    public Integer getCommentCount(Integer taskID) {
        return  commentRepository.getCommentCount(taskID);
    }

    public Comment addComment(CommentResponse commentResponse) {
        Comment comment1 = new Comment(commentResponse);

        comment1 = commentRepository.save(comment1);
        return comment1;
    }

    public String deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
        return "Comment is deleted successfully!";
    }
}
