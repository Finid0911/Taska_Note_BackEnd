package com.example.TaN02.entity;

import com.example.TaN02.response.PlansResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "plans")
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlanID")
    private Integer planID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Cover")
    private String cover;

    @Column(name = "Numoftask")
    private Integer numOfTask;

    @Column(name = "Createdtime")
    private String createdTime;

    @Column(name = "Isfavorite")
    private Integer isFavorite;

    @ManyToOne
    @JoinColumn(name = "IconID")
    private Icon icon;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users users;

    public Plans(PlansResponse plansResponse) {
        this.title = plansResponse.getTitle();
        this.description = plansResponse.getDescription();;
        this.cover = plansResponse.getCover();
        this.numOfTask = plansResponse.getNumOfTask();
        this.createdTime = plansResponse.getCreatedTime();
        this.isFavorite = plansResponse.getIsFavorite();
        this.icon = new Icon();
        this.users = new Users();

        if(plansResponse.getIconID() != null) {
            this.icon.setIconID(plansResponse.getIconID());
        }

        if (plansResponse.getUserID() != null) {
            this.users.setUserID(plansResponse.getUserID());
        }
    }
}
