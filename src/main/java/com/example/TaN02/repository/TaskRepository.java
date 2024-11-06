package com.example.TaN02.repository;

import com.example.TaN02.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.plans.planID = :planId")
    List<Task> getTaskByPlanId(Integer planId);

    @Query("SELECT t FROM Task t " +
            "JOIN UpdateStatus us ON t.taskID = us.task.taskID " +
            "WHERE us.status.statusID = :statusID AND t.plans.planID = :planID " +
            "GROUP BY t.taskID " +
            "HAVING MAX(us.UpdatedTime) = ( " +
            "SELECT MAX(u2.UpdatedTime) FROM UpdateStatus u2 " +
            "WHERE u2.task.taskID = t.taskID " +
            ") " +
            "ORDER BY MAX(us.UpdatedTime) DESC")
    List<Task> findTasksByStatusIDWithLatestUpdate(Integer planID, Integer statusID);

    @Query("SELECT t FROM Task t " +
            "JOIN UpdateStatus us ON t.taskID = us.task.taskID " +
            "WHERE us.status.statusID = :statusID AND t.plans.planID = :planID " +
            "AND us.UpdatedTime = (SELECT MAX(u.UpdatedTime) FROM UpdateStatus u WHERE u.task.taskID = t.taskID)")
    List<Task> findTasksByStatusID(Integer planID, Integer statusID);

    @Query("SELECT t FROM Task t " +
            "JOIN UpdateStatus us ON t.taskID = us.task.taskID " +
            "WHERE us.status.statusID = :statusID AND t.plans.planID = :planID " +
            "AND t.taskID = :taskID " +
            "GROUP BY t.taskID " +
            "HAVING MAX(us.UpdatedTime) = ( " +
            "SELECT MAX(u2.UpdatedTime) FROM UpdateStatus u2 " +
            "WHERE u2.task.taskID = t.taskID " +
            ") " +
            "ORDER BY MAX(us.UpdatedTime) DESC")
    Task getTaskByTaskIdForDetail(Integer planID, Integer statusID, Integer taskID);

    @Query("SELECT t FROM Task t " +
            "JOIN UpdateStatus us ON t.taskID = us.task.taskID " +
            "JOIN Plans p ON t.plans.planID = p.planID " +
            "JOIN Users u ON p.users.userID = u.userID " +
            "WHERE u.userID = :userId AND (us.status.statusID = 1 OR us.status.statusID = 2) " +
            "GROUP BY t.taskID " +
            "HAVING MAX(us.UpdatedTime) = ( " +
            "SELECT MAX(u2.UpdatedTime) FROM UpdateStatus u2 " +
            "WHERE u2.task.taskID = t.taskID) " +
            "ORDER BY MAX(us.UpdatedTime) DESC")
    List<Task> getTaskInListView(Integer userId);
}
