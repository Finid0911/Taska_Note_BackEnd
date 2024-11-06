package com.example.TaN02.entity;

import com.example.TaN02.response.StatusResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StatusID")
    private Integer statusID;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ColorID")
    private Color color;

    public Status(StatusResponse statusResponse) {
        this.title = statusResponse.getTitle();
        this.color = new Color();
        if (statusResponse.getColorID() != null) {
            this.color.setColorID(statusResponse.getColorID());
        }
    }
}
