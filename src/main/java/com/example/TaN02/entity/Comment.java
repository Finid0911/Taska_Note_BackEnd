package com.example.TaN02.entity;

import com.example.TaN02.response.CommentResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private Integer commentID;

    @Column(name = "Content")
    private String content;

    @Column(name = "Createdtime")
    private String createdTime;

    @ManyToOne
    @JoinColumn(name = "TaskID")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users users;

    public Comment(CommentResponse commentResponse) {
        this.content = commentResponse.getContent();
        this.createdTime = commentResponse.getCreatedTime();
        this.task = new Task();
        if (commentResponse.getTaskID() != null) {
            this.task.setTaskID(commentResponse.getTaskID());
        }
        this.users = new Users();
        if (commentResponse.getUserID() != null) {
            this.users.setUserID(commentResponse.getUserID());
        }
    }
}
