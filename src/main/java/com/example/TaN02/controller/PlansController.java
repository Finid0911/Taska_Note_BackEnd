package com.example.TaN02.controller;

import com.example.TaN02.entity.Plans;
import com.example.TaN02.response.PlansResponse;
import com.example.TaN02.service.PlansService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plans")
public class PlansController {

    @Autowired
    PlansService plansService;

    @GetMapping("/getAllPlans")
    public List<PlansResponse> getAllPlans() {
        List<Plans> plansList = plansService.getAllPlans();
        List<PlansResponse> plansResponseList = new ArrayList<>();

        plansList.forEach(plans -> {
            plansResponseList.add(new PlansResponse(plans));
        });
        return plansResponseList;
    }

    @GetMapping("/getAllPlansByUserId")
    public List<PlansResponse> getAllPlansByUserId(@RequestParam Integer userID) {
        List<Plans> plansList = plansService.getPlanListByUserId(userID);
        List<PlansResponse> plansResponseList = new ArrayList<>();

        plansList.forEach(plans -> {
            plansResponseList.add(new PlansResponse(plans));
        });
        return plansResponseList;
    }

    @GetMapping("/getAllPlansByUserId/{userID}")
    public List<PlansResponse> getAllPlansByUserId1(@PathVariable("userID") Integer userID) {
        List<Plans> plansList = plansService.getPlanListByUserId(userID);
        List<PlansResponse> plansResponseList = new ArrayList<>();

        plansList.forEach(plans -> {
            plansResponseList.add(new PlansResponse(plans));
        });
        return plansResponseList;
    }

    @GetMapping("/getPlanListByUserIdAndIsFav/{userID}/{isFav}")
    public List<PlansResponse> getPlanListByUserIdAndIsFav(@PathVariable("userID") Integer userID, @PathVariable("isFav") Integer isFav) {
        List<Plans> plansList = plansService.getPlanListByUserIdAndIsFav(userID, isFav);
        List<PlansResponse> plansResponseList = new ArrayList<>();

        plansList.forEach(plans -> {
            plansResponseList.add(new PlansResponse(plans));
        });
        return plansResponseList;
    }

    @GetMapping
    public List<PlansResponse> getPlanById(@RequestParam Integer planId) {
        Optional<Plans> plansList = plansService.getPlanById(planId);
        List<PlansResponse> plansResponseList = new ArrayList<>();

        plansList.ifPresent(plans -> {
            plansResponseList.add(new PlansResponse(plans));
        });
        return plansResponseList;
    }

    @GetMapping("/getPlanById/{planId}")
    public PlansResponse getPlanById1(@PathVariable("planId") Integer planId) {
        Plans plans = plansService.getPlanById1(planId);

        return new PlansResponse(plans);
    }

    @PostMapping("/addNewPlan")
    public PlansResponse addNewPlan(@Valid @RequestBody PlansResponse plansResponse) {
        Plans plan = plansService.addNewPlan(plansResponse);

        return new PlansResponse(plan);
    }

    @PutMapping("/updatePlan")
    public PlansResponse updatePlan(@Valid @RequestBody PlansResponse plansResponse) {
        Plans plan = plansService.updatePlan(plansResponse);

        return new PlansResponse(plan);
    }

    @DeleteMapping("/deletePlan")
    public String deletePlan1(@RequestParam Integer planId) {
        return plansService.deletePlan1(planId);
    }

    @DeleteMapping("/deletePlan/{planId}")
    public String deletePlan(@PathVariable("planId") Integer planId) {
        return plansService.deletePlan(planId);
    }
}
