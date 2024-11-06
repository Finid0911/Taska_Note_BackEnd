package com.example.TaN02.controller;

import com.example.TaN02.entity.Priority;
import com.example.TaN02.response.PriorityResponse;
import com.example.TaN02.service.PriorityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {

    @Autowired
    PriorityService priorityService;

    @GetMapping("/getAllPriority")
    public List<PriorityResponse> getAllPriority() {
        List<Priority> priorityList = priorityService.getAllPriority();
        List<PriorityResponse> priorityResponseList = new ArrayList<>();

        priorityList.forEach(priority -> {
            priorityResponseList.add(new PriorityResponse(priority));
        });

        return priorityResponseList;
    }

    @GetMapping
    public List<PriorityResponse> getPriorityById(@RequestParam Integer priorityId) {
        Optional<Priority> priorityList = priorityService.getPriorityById(priorityId);
        List<PriorityResponse> priorityResponseList = new ArrayList<>();

        priorityList.ifPresent(priority -> {
            priorityResponseList.add(new PriorityResponse(priority));
        });

        return priorityResponseList;
    }

    @GetMapping("/getPriorityById/{priorityId}")
    public PriorityResponse getPriorityById1(@PathVariable("priorityId") Integer priorityId) {
        Priority priority = priorityService.getPriorityById1(priorityId);

        return new PriorityResponse(priority);
    }

    @PostMapping("/addNewPriority")
    public PriorityResponse addNewPriority(@Valid @RequestBody PriorityResponse priorityResponse) {
        Priority priority = priorityService.addNewPriority(priorityResponse);

        return new PriorityResponse(priority);
    }

    @PutMapping("/updatePriority")
    public PriorityResponse updatePriority(@Valid @RequestBody PriorityResponse priorityResponse) {
        Priority priority = priorityService.updatePriority(priorityResponse);

        return new PriorityResponse(priority);
    }

    @DeleteMapping("/deletePriority")
    public String deletePriority(@RequestParam Integer priorityId) {
        return priorityService.deletePriority(priorityId);
    }
}
