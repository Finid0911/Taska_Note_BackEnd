package com.example.TaN02.response;

import com.example.TaN02.entity.Icon;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IconResponse {

    @JsonProperty("icon_id")
    private Integer iconID;

    @JsonProperty("icon_name")
    private String iconName;

    @JsonProperty("image")
    private String image;

    public IconResponse(Icon icon) {
        this.iconID = icon.getIconID();
        this.iconName = icon.getIconName();
        this.image = icon.getImage();
    }
}
