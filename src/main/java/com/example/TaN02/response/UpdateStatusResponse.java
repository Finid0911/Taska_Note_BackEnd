package com.example.TaN02.response;

import com.example.TaN02.entity.Status;
import com.example.TaN02.entity.Task;
import com.example.TaN02.entity.UpdateStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStatusResponse {

    @JsonProperty("updated_time")
    private String UpdatedTime;

    @JsonProperty("task_id")
    private Integer taskID;

    @JsonProperty("status_id")
    private Integer statusID;

    public UpdateStatusResponse(UpdateStatus updateStatus) {
        this.UpdatedTime = updateStatus.getUpdatedTime();
        this.taskID = updateStatus.getTask().getTaskID();
        this.statusID = updateStatus.getStatus().getStatusID();
    }
}
