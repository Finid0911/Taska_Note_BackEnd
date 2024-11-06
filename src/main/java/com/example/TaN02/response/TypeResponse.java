package com.example.TaN02.response;

import com.example.TaN02.entity.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeResponse {

    @JsonProperty("type_id")
    private Integer typeID;

    @NotBlank(message = "Type Title must not be blank!")
    @JsonProperty("title")
    private String title;

    @JsonProperty("color_id")
    private Integer colorID;

    public TypeResponse(Type type) {
        this.typeID = type.getTypeID();
        this.title = type.getTitle();
        this.colorID = type.getColor().getColorID();
    }

}
