package com.example.TaN02.entity;

import com.example.TaN02.response.UpdateStatusResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "update_status")
public class UpdateStatus {

    @Id
    @Column(name = "Updatedtime")
    private String UpdatedTime;

    @ManyToOne
    @JoinColumn(name = "TaskID")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "StatusID")
    private Status status;

    public UpdateStatus(UpdateStatusResponse updateStatusResponse) {
        this.UpdatedTime = updateStatusResponse.getUpdatedTime();
        this.task = new Task();
        this.status = new Status();

        if(updateStatusResponse.getTaskID() != null) {
            this.task.setTaskID(updateStatusResponse.getTaskID());
        }

        if(updateStatusResponse.getStatusID() != null) {
            this.status.setStatusID(updateStatusResponse.getStatusID());
        }
    }

}
