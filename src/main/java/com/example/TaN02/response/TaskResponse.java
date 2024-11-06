package com.example.TaN02.response;

import com.example.TaN02.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponse {

    @JsonProperty("task_id")
    private Integer taskID;

    @JsonProperty("title")
    private String title;

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("sub_task")
    private String subTask;

    @JsonProperty("priority_id")
    private Integer priorityID;

    @JsonProperty("type_id")
    private Integer typeID;

    @JsonProperty("plan_id")
    private Integer planID;

    @JsonProperty("icon_id")
    private Integer iconID;

    public TaskResponse(Task task) {
        this.taskID = task.getTaskID();
        this.title = task.getTitle();
        this.createdTime = task.getCreatedTime();
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
        this.subTask = task.getSubTask();
        this.priorityID = task.getPriority().getPriorityID();
        this.typeID = task.getType().getTypeID();
        this.planID = task.getPlans().getPlanID();
        this.iconID = task.getIcon().getIconID();
    }

}
