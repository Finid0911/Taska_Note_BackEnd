package com.example.TaN02.service;

import com.example.TaN02.entity.*;
import com.example.TaN02.repository.*;
import com.example.TaN02.response.PlansResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlansService {

    @Autowired
    PlansRepository plansRepository;

    @Autowired
    IconRepository iconRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UpdateStatusRepository updateStatusRepository;

    @Autowired
    CommentRepository commentRepository;

    public List<Plans> getAllPlans() {
        return plansRepository.findAll();
    }

    public List<Plans> getPlanListByUserId(Integer userId) {
        return plansRepository.getPlanListByUserId(userId);
    }

    public List<Plans> getPlanListByUserIdAndIsFav(Integer userId, Integer isFavorite) {
        return plansRepository.getPlanListByUserIdAndIsFav(userId, isFavorite);
    }

    public Optional<Plans> getPlanById(Integer planId) {
        return plansRepository.findById(planId);
    }

    public Plans getPlanById1(Integer planId) {
        return plansRepository.getPlanById(planId);
    }

    public Plans addNewPlan(PlansResponse plansResponse) {
        Plans plan = new Plans(plansResponse);

        plan = plansRepository.save(plan);
        return plan;
    }

    public Plans updatePlan(PlansResponse plansResponse) {
        Plans plan = plansRepository.findById(plansResponse.getPlanID()).get();

        if (!plansResponse.getTitle().isEmpty() && !plansResponse.getTitle().isBlank()) {
            plan.setTitle(plansResponse.getTitle());
        }
        plan.setDescription(plansResponse.getDescription());
        plan.setCover(plansResponse.getCover());
        plan.setNumOfTask(plansResponse.getNumOfTask());
        plan.setIsFavorite(plansResponse.getIsFavorite());

        Optional<Icon> iconOptional = iconRepository.findById(plansResponse.getIconID());
        iconOptional.ifPresent(plan::setIcon);

        plan = plansRepository.save(plan);

        return plan;
    }

    public String deletePlan1(Integer planId) {
        plansRepository.deleteById(planId);
        return "Plan is deleted successfully!";
    }

    public String deletePlan(Integer planId) {
        List<Task> taskList = taskRepository.getTaskByPlanId(planId);
        if (!taskList.isEmpty()) {
            for (Task task : taskList) {
                List<UpdateStatus> updateStatusList = updateStatusRepository.findByTaskId(task.getTaskID());
                if (!updateStatusList.isEmpty()) {
                    for (UpdateStatus updateStatus : updateStatusList) {
                        updateStatusRepository.delete(updateStatus);
                    }
                }

                List<Comment> commentList = commentRepository.getCommentByTaskId(task.getTaskID());
                if (!commentList.isEmpty()) {
                    for (Comment comment: commentList) {
                        commentRepository.delete(comment);
                    }
                }
                taskRepository.delete(task);
            }
        }
        plansRepository.deleteById(planId);
        return "Plan is deleted successfully!";
    }

}
