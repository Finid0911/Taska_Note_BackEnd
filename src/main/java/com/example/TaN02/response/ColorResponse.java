package com.example.TaN02.response;

import com.example.TaN02.entity.Color;
import com.example.TaN02.entity.Priority;
import com.example.TaN02.entity.Status;
import com.example.TaN02.entity.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ColorResponse {

    @JsonProperty("color_id")
    private Integer colorID;

    @NotBlank(message = "Color name must not be blank!")
    @JsonProperty("color_name")
    private String colorName;

    @NotBlank(message = "Color code must not be blank!")
    @JsonProperty("color_code")
    private String colorCode;

    @JsonProperty("priority_color")
    private List<PriorityResponse> priorityColor;

    @JsonProperty("type_color")
    private List<TypeResponse> typeColor;

    @JsonProperty("status_color")
    private List<StatusResponse> statusColor;

    public ColorResponse(Color color) {
        this.colorID = color.getColorID();
        this.colorName = color.getColorName();
        this.colorCode = color.getColorCode();

        if (color.getPriorityColor() != null) {
            priorityColor = new ArrayList<>();
            for (Priority priority : color.getPriorityColor()) {
                priorityColor.add(new PriorityResponse(priority));
            }
        }

        if (color.getTypeColor() != null) {
            typeColor = new ArrayList<>();
            for (Type type : color.getTypeColor()) {
                typeColor.add(new TypeResponse(type));
            }
        }

        if (color.getStatusColor() != null) {
            statusColor = new ArrayList<>();
            for (Status status : color.getStatusColor()) {
                statusColor.add(new StatusResponse(status));
            }
        }
    }

}
