package com.example.TaN02.entity;

import com.example.TaN02.response.ColorResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ColorID")
    private Integer colorID;

    @Column(name = "Colorname")
    private String colorName;

    @Column(name = "Colorcode")
    private String colorCode;

    @OneToMany(mappedBy = "color")
    private List<Priority> priorityColor;

    @OneToMany(mappedBy = "color")
    private List<Type> typeColor;

    @OneToMany(mappedBy = "color")
    private List<Status> statusColor;

    public Color(String colorName, String colorCode) {
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public Color(ColorResponse colorResponse) {
        this.colorName = colorResponse.getColorName();
        this.colorCode = colorResponse.getColorCode();
    }
}
