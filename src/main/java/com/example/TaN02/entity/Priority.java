package com.example.TaN02.entity;

import com.example.TaN02.response.PriorityResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "priority")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriorityID")
    private Integer priorityID;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ColorID")
    private Color color;

    public Priority(PriorityResponse priorityResponse) {
        this.title = priorityResponse.getTitle();
        this.color = new Color();
        if (priorityResponse.getColorID() != null) {
            this.color.setColorID(priorityResponse.getColorID());
        }
    }

}
