package com.example.TaN02.response;

import com.example.TaN02.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {

    @JsonProperty("comment_id")
    private Integer commentID;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("task_id")
    private Integer taskID;

    @JsonProperty("user_id")
    private Integer userID;

    public CommentResponse(Comment comment) {
        this.commentID = comment.getCommentID();
        this.content = comment.getContent();
        this.createdTime = comment.getCreatedTime();
        this.taskID = comment.getTask().getTaskID();
        this.userID = comment.getUsers().getUserID();
    }

}
