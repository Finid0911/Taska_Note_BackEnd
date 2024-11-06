package com.example.TaN02.entity;

import com.example.TaN02.response.IconResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "icon")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IconID")
    private Integer iconID;

    @Column(name = "Iconname")
    private String iconName;

    @Column(name = "Image")
    private String image;

    public Icon(String iconName, String image) {
        this.iconName = iconName;
        this.image = image;
    }

    public Icon(IconResponse iconResponse) {
        this.iconName = iconResponse.getIconName();
        this.image = iconResponse.getImage();
    }
}
