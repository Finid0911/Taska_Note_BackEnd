package com.example.TaN02.entity;

import com.example.TaN02.response.TypeResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private Integer typeID;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ColorID")
    private Color color;

    public Type(TypeResponse typeResponse) {
        this.title = typeResponse.getTitle();
        this.color = new Color();
        if (typeResponse.getColorID() != null) {
            this.color.setColorID(typeResponse.getColorID());
        }
    }
}
