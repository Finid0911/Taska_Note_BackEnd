package com.example.TaN02.repository;

import com.example.TaN02.entity.UpdateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateStatusRepository extends JpaRepository<UpdateStatus, Void> {

    @Query("SELECT us FROM UpdateStatus us " +
            "WHERE us.status.statusID = :statusID AND us.task.taskID = :taskID " +
            "ORDER BY us.UpdatedTime DESC")
    List<UpdateStatus> findCustomByStatusIDAndTaskID(Integer statusID, Integer taskID);

    @Query("SELECT us FROM UpdateStatus us WHERE us.task.taskID = :taskID")
    List<UpdateStatus> findByTaskId(Integer taskID);

    @Query("SELECT us FROM UpdateStatus us " +
            "WHERE us.task.taskID = :taskId " +
            "AND us.UpdatedTime = (SELECT MAX(us2.UpdatedTime) FROM UpdateStatus us2 WHERE us2.task.taskID = :taskId)")
    UpdateStatus getLatestUpdateStatusByTaskId(Integer taskId);

}
