package com.example.TaN02.controller;

import com.example.TaN02.entity.Comment;
import com.example.TaN02.response.CommentResponse;
import com.example.TaN02.response.IconResponse;
import com.example.TaN02.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/getAllComments")
    public List<CommentResponse> getAllComments() {
        List<Comment> commentList = commentService.getAllComments();
        List<CommentResponse> commentResponseList = new ArrayList<>();

        commentList.forEach(comment -> {
            commentResponseList.add(new CommentResponse(comment));
        });
        return commentResponseList;
    }

    @GetMapping("/getCommentByTaskId/{taskID}")
    public List<CommentResponse> getCommentByTaskId(@PathVariable("taskID") Integer taskID) {
        List<Comment> commentList = commentService.getCommentByTaskId(taskID);
        List<CommentResponse> commentResponseList = new ArrayList<>();

        commentList.forEach(comment -> {
            commentResponseList.add(new CommentResponse(comment));
        });
        return commentResponseList;
    }

    @GetMapping("/getCommentCount/{taskID}")
    public Integer getCommentCount(@PathVariable("taskID") Integer taskID) {
        return commentService.getCommentCount(taskID);
    }

    @PostMapping("/addNewComment")
    public CommentResponse addNewComment(@Valid @RequestBody CommentResponse commentResponse) {
        Comment comment = commentService.addComment(commentResponse);

        return new CommentResponse(comment);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId) {
        return commentService.deleteComment(commentId);
    }
}
