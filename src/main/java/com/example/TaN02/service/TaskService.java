package com.example.TaN02.service;

import com.example.TaN02.entity.*;
import com.example.TaN02.repository.*;
import com.example.TaN02.response.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    IconRepository iconRepository;

    @Autowired
    UpdateStatusRepository updateStatusRepository;

    @Autowired
    CommentRepository commentRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Integer taskId) {
        return taskRepository.findById(taskId);
    }

    public List<Task> getTaskByPlanId(Integer planId) {
        return taskRepository.getTaskByPlanId(planId);
    }

    public List<Task> getTaskByUserId(Integer userId) {
        return taskRepository.getTaskInListView(userId);
    }

    public List<Task> getPlanByStatus1(Integer planId, Integer statusId) {
        return taskRepository.findTasksByStatusIDWithLatestUpdate(planId, statusId);
    }

    public List<Task> getPlanByStatus2(Integer planId, Integer statusId) {
        return taskRepository.findTasksByStatusID(planId, statusId);
    }

    public Task getTaskByTaskIdForDetail(Integer planId, Integer statusId, Integer taskId) {
        return taskRepository.getTaskByTaskIdForDetail(planId, statusId, taskId);
    }

    public Task addNewTask(TaskResponse taskResponse) {
        Task task = new Task(taskResponse);

        task = taskRepository.save(task);
        return task;
    }

    public Task updateTask(TaskResponse taskResponse) {
        Task task = taskRepository.findById(taskResponse.getTaskID()).get();

        if (!taskResponse.getTitle().isEmpty() && !taskResponse.getTitle().isBlank()) {
            task.setTitle(taskResponse.getTitle());
        }
        if (!taskResponse.getStartDate().isEmpty() && !taskResponse.getStartDate().isBlank()) {
            task.setStartDate(taskResponse.getStartDate());
        }
        if (!taskResponse.getStartTime().isEmpty() && !taskResponse.getStartTime().isBlank()) {
            task.setStartTime(taskResponse.getStartTime());
        }

        task.setEndDate(taskResponse.getEndDate());
        task.setEndTime(taskResponse.getEndTime());
        task.setSubTask(taskResponse.getSubTask());

        Optional<Priority> optionalPriority = priorityRepository.findById(taskResponse.getPriorityID());
        optionalPriority.ifPresent(task::setPriority);

        Optional<Type> optionalType = typeRepository.findById(taskResponse.getTypeID());
        optionalType.ifPresent(task::setType);

        Optional<Icon> optionalIcon = iconRepository.findById(taskResponse.getIconID());
        optionalIcon.ifPresent(task::setIcon);

        task = taskRepository.save(task);
        return task;
    }

    public String deleteTask(Integer taskId) {
        List<UpdateStatus> updateStatusList = updateStatusRepository.findByTaskId(taskId);
        if (!updateStatusList.isEmpty()) {
            for (UpdateStatus updateStatus : updateStatusList) {
                updateStatusRepository.delete(updateStatus);
            }
        }

        List<Comment> commentList = commentRepository.getCommentByTaskId(taskId);
        if (!commentList.isEmpty()) {
            for (Comment comment: commentList) {
                commentRepository.delete(comment);
            }
        }
        taskRepository.deleteById(taskId);
        return "Task is deleted successfully!";
    }
}
