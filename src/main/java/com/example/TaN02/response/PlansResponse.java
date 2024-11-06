package com.example.TaN02.response;

import com.example.TaN02.entity.Plans;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlansResponse {

    @JsonProperty("plan_id")
    private Integer planID;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("num_of_task")
    private Integer numOfTask;

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("is_favorite")
    private Integer isFavorite;

    @JsonProperty("icon_id")
    private Integer iconID;

    @JsonProperty("user_id")
    private Integer userID;

    public PlansResponse(Plans plans) {
        this.planID = plans.getPlanID();
        this.title = plans.getTitle();
        this.description = plans.getDescription();
        this.cover = plans.getCover();
        this.numOfTask = plans.getNumOfTask();
        this.createdTime = plans.getCreatedTime();
        this.isFavorite = plans.getIsFavorite();
        this.iconID = plans.getIcon().getIconID();
        this.userID = plans.getUsers().getUserID();
    }

}
