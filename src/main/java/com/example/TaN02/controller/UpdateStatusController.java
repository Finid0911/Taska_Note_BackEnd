package com.example.TaN02.controller;

import com.example.TaN02.entity.UpdateStatus;
import com.example.TaN02.response.UpdateStatusResponse;
import com.example.TaN02.service.UpdateStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/updatestatus")
public class UpdateStatusController {

    @Autowired
    UpdateStatusService updateStatusService;

    @GetMapping("/getAllUpdateStatus")
    public List<UpdateStatusResponse> getAllUpdateStatus() {
        List<UpdateStatus> updateStatusList = updateStatusService.getAllUpdateStatus();
        List<UpdateStatusResponse> updateStatusResponses = new ArrayList<>();

        updateStatusList.forEach(data -> {
            updateStatusResponses.add(new UpdateStatusResponse(data));
        });
        return updateStatusResponses;
    }

    @GetMapping("/getAllUpdateStatusByIds/{statusId}/{taskId}")
    public List<UpdateStatusResponse> getAllUpdateStatusByIds(@PathVariable("statusId") Integer statusId, @PathVariable("taskId") Integer taskId) {
        List<UpdateStatus> updateStatusList = updateStatusService.getUpdateStatusByStatusIDAndTaskId(statusId, taskId);
        List<UpdateStatusResponse> updateStatusResponses = new ArrayList<>();

        updateStatusList.forEach(data -> {
            updateStatusResponses.add(new UpdateStatusResponse(data));
        });
        return updateStatusResponses;
    }

    @GetMapping("/getLatestUpdateStatusByTaskId/{taskId}")
    public UpdateStatusResponse getLatestUpdateStatusByTaskId(@PathVariable("taskId") Integer taskId) {
        UpdateStatus updateStatus = updateStatusService.getLatestUpdateStatusByTaskId(taskId);

        return new UpdateStatusResponse(updateStatus);
    }

    @PostMapping("/addUpdateStatus")
    public UpdateStatusResponse addNewUpdateStatus(@Valid @RequestBody UpdateStatusResponse updateStatusResponse) {
        UpdateStatus updateStatus = updateStatusService.addUpdateStatus(updateStatusResponse);

        return new UpdateStatusResponse(updateStatus);
    }

    @DeleteMapping("/deleteUpdateStatus/{taskId}")
    public String deleteUpdateStatus(@PathVariable("taskId") Integer taskId) {
        return updateStatusService.deleteUpdateStatus(taskId);
    }
}
