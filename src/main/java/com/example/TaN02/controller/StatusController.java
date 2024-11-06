package com.example.TaN02.controller;

import com.example.TaN02.entity.Priority;
import com.example.TaN02.entity.Status;
import com.example.TaN02.entity.Task;
import com.example.TaN02.response.PriorityResponse;
import com.example.TaN02.response.StatusResponse;
import com.example.TaN02.response.TaskResponse;
import com.example.TaN02.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @GetMapping("/getAllStatus")
    public List<StatusResponse> getAllStatus() {
        List<Status> statusList = statusService.getAllStatus();
        List<StatusResponse> statusResponseList = new ArrayList<>();

        statusList.forEach(status -> {
            statusResponseList.add(new StatusResponse(status));
        });

        return statusResponseList;
    }

    @GetMapping
    public List<StatusResponse> getStatusById(@RequestParam Integer statusId) {
        Optional<Status> statusOptional = statusService.getStatusById(statusId);
        List<StatusResponse> statusResponseList = new ArrayList<>();

        statusOptional.ifPresent(status -> {
            statusResponseList.add(new StatusResponse(status));
        });

        return statusResponseList;
    }

    @GetMapping("/getStatusById/{statusId}")
    public StatusResponse getStatusById1(@PathVariable("statusId") Integer statusId) {
        Status status = statusService.getStatusById1(statusId);

        return new StatusResponse(status);
    }

    @PostMapping("/addNewStatus")
    public StatusResponse addNewStatus(@Valid @RequestBody StatusResponse statusResponse) {
        Status status = statusService.addNewStatus(statusResponse);

        return new StatusResponse(status);
    }

    @PutMapping("/updateStatus")
    public StatusResponse updateStatus(@Valid @RequestBody StatusResponse statusResponse) {
        Status status = statusService.addNewStatus(statusResponse);

        return new StatusResponse(status);
    }

    @DeleteMapping("/deleteStatus")
    public String deleteStatus(@RequestParam Integer statusId) {
        return statusService.deleteStatus(statusId);
    }

}
