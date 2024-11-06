package com.example.TaN02.service;

import com.example.TaN02.entity.Task;
import com.example.TaN02.entity.UpdateStatus;
import com.example.TaN02.repository.TaskRepository;
import com.example.TaN02.repository.UpdateStatusRepository;
import com.example.TaN02.response.UpdateStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UpdateStatusService {

    @Autowired
    UpdateStatusRepository updateStatusRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<UpdateStatus> getAllUpdateStatus() {
        return updateStatusRepository.findAll();
    }

    public List<UpdateStatus> getUpdateStatusByStatusIDAndTaskId(Integer statusId, Integer taskId) {
        return updateStatusRepository.findCustomByStatusIDAndTaskID(statusId, taskId);
    }

    public UpdateStatus getLatestUpdateStatusByTaskId(Integer taskId) {
        return updateStatusRepository.getLatestUpdateStatusByTaskId(taskId);
    }

    public UpdateStatus addUpdateStatus(UpdateStatusResponse updateStatusResponse) {
        UpdateStatus updateStatus = new UpdateStatus(updateStatusResponse);

        updateStatus = updateStatusRepository.save(updateStatus);
        return updateStatus;
    }

    public String deleteUpdateStatus(Integer taskId) {
        List<UpdateStatus> updateStatusList = updateStatusRepository.findByTaskId(taskId);
        for (UpdateStatus us: updateStatusList) {
            updateStatusRepository.delete(us);
        }
        return "Delete UpdateStatus successfully";
    }

}
