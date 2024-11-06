package com.example.TaN02.response;

import com.example.TaN02.entity.Priority;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriorityResponse {

    @JsonProperty("priority_id")
    private Integer priorityID;

    @NotBlank(message = "Priority Title must not be blank!")
    @JsonProperty("title")
    private String title;

    @JsonProperty("color_id")
    private Integer colorID;

//    @JsonProperty("color_name")
//    private String colorName;
//
//    @JsonProperty("color_code")
//    private String colorCode;

    public PriorityResponse(Priority priority) {
        this.priorityID = priority.getPriorityID();
        this.title = priority.getTitle();
        this.colorID = priority.getColor().getColorID();
//        this.colorName = priority.getColor().getColorName();
//        this.colorCode = priority.getColor().getColorCode();
    }
}
