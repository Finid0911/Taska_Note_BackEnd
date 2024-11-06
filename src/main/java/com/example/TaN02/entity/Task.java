package com.example.TaN02.entity;

import com.example.TaN02.response.TaskResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskID")
    private Integer taskID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Createdtime")
    private String createdTime;

    @Column(name = "Startdate")
    private String startDate;

    @Column(name = "Enddate")
    private String endDate;

    @Column(name = "Starttime")
    private String startTime;

    @Column(name = "Endtime")
    private String endTime;

    @Column(name = "Subtask")
    private String subTask;

    @ManyToOne
    @JoinColumn(name = "PriorityID")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "TypeID")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "PlanID")
    private Plans plans;

    @ManyToOne
    @JoinColumn(name = "IconID")
    private Icon icon;

    public Task(TaskResponse taskResponse) {
        this.title = taskResponse.getTitle();
        this.createdTime = taskResponse.getCreatedTime();
        this.startDate = taskResponse.getStartDate();
        this.endDate = taskResponse.getEndDate();
        this.startTime = taskResponse.getStartTime();
        this.endTime = taskResponse.getEndTime();
        this.subTask = taskResponse.getSubTask();
        this.priority = new Priority();
        this.type = new Type();
        this.plans = new Plans();
        this.icon = new Icon();

        if (taskResponse.getPriorityID() != null) {
            this.priority.setPriorityID(taskResponse.getPriorityID());
        }

        if (taskResponse.getTypeID() != null) {
            this.type.setTypeID(taskResponse.getTypeID());
        }

        if (taskResponse.getPlanID() != null) {
            this.plans.setPlanID(taskResponse.getPlanID());
        }

        if (taskResponse.getIconID() != null) {
            this.icon.setIconID(taskResponse.getIconID());
        }
    }
}
