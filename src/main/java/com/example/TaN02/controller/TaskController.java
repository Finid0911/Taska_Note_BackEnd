package com.example.TaN02.controller;

import com.example.TaN02.entity.Task;
import com.example.TaN02.response.TaskResponse;
import com.example.TaN02.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/getAllTasks")
    public List<TaskResponse> getAllTasks() {
        List<Task> taskList = taskService.getAllTasks();
        List<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach(task -> {
            taskResponseList.add(new TaskResponse(task));
        });

        return taskResponseList;
    }

    @GetMapping
    public List<TaskResponse> getTaskById(@RequestParam Integer taskId) {
        Optional<Task> taskList = taskService.getTaskById(taskId);
        List<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.ifPresent(task -> {
            taskResponseList.add(new TaskResponse(task));
        });

        return taskResponseList;
    }

    @GetMapping("/getTaskByPlanId/{planId}")
    public List<TaskResponse> getTaskByPlanId(@PathVariable("planId") Integer planId) {
        List<Task> taskList = taskService.getTaskByPlanId(planId);
        List<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach(task -> {
            taskResponseList.add(new TaskResponse(task));
        });
        return taskResponseList;
    }

    @GetMapping("/getTaskByUserId/{userId}")
    public List<TaskResponse> getTaskByUserId(@PathVariable("userId") Integer userId) {
        List<Task> taskList = taskService.getTaskByUserId(userId);
        List<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach(task -> {
            taskResponseList.add(new TaskResponse(task));
        });
        return taskResponseList;
    }

    @GetMapping("/getTaskByStatus1/{planId}/{statusId}")
    public List<TaskResponse> getTaskByStatus1(@PathVariable("planId") Integer planId, @PathVariable("statusId") Integer statusId) {
        List<Task> taskList = taskService.getPlanByStatus1(planId, statusId);
        List<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach(task -> {
            taskResponseList.add(new TaskResponse(task));
        });
        return taskResponseList;
    }

    @GetMapping("/getTaskByStatus2/{planId}/{statusId}")
    public List<TaskResponse> getTaskByStatus2(@PathVariable("planId") Integer planId, @PathVariable("statusId") Integer statusId) {
        List<Task> taskList = taskService.getPlanByStatus2(planId, statusId);
        List<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach(task -> {
            taskResponseList.add(new TaskResponse(task));
        });
        return taskResponseList;
    }

    @GetMapping("/getTaskByTaskIdForDetail/{planId}/{statusId}/{taskId}")
    public TaskResponse getTaskByTaskIdForDetail(@PathVariable("planId") Integer planId, @PathVariable("statusId") Integer statusId, @PathVariable("taskId") Integer taskId) {
        Task task = taskService.getTaskByTaskIdForDetail(planId, statusId, taskId);

        return new TaskResponse(task);
    }

    @PostMapping("/addNewTask")
    public TaskResponse addNewTask(@Valid @RequestBody TaskResponse taskResponse) {
        Task task = taskService.addNewTask(taskResponse);

        return new TaskResponse(task);
    }

    @PutMapping("/updateTask")
    public TaskResponse updateTask(@Valid @RequestBody TaskResponse taskResponse) {
        Task task = taskService.updateTask(taskResponse);

        return new TaskResponse(task);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable("taskId") Integer taskId) {
        return taskService.deleteTask(taskId);
    }

}
