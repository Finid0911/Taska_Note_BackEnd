package com.example.TaN02.response;

import com.example.TaN02.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatusResponse {

    @JsonProperty("status_id")
    private Integer statusID;

    @NotBlank(message = "Status Title must not be blank!")
    @JsonProperty("title")
    private String title;

    @JsonProperty("color_id")
    private Integer colorID;

    public StatusResponse(Status status) {
        this.statusID = status.getStatusID();
        this.title = status.getTitle();
        this.colorID = status.getColor().getColorID();
    }

}
