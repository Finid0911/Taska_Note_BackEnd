package com.example.TaN02.service;

import com.example.TaN02.entity.Color;
import com.example.TaN02.entity.Priority;
import com.example.TaN02.repository.ColorRepository;
import com.example.TaN02.repository.PriorityRepository;
import com.example.TaN02.response.PriorityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {

    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    ColorRepository colorRepository;

    public List<Priority> getAllPriority() {
        return priorityRepository.findAll();
    }

    public Optional<Priority> getPriorityById(Integer priorityId) {
        return priorityRepository.findById(priorityId);
    }

    public Priority getPriorityById1(Integer priorityId) {
        return priorityRepository.getPriorityById(priorityId);
    }

    public Priority addNewPriority(PriorityResponse priorityResponse) {
        Priority priority = new Priority(priorityResponse);

        priority = priorityRepository.save(priority);
        return priority;
    }

    public Priority updatePriority(PriorityResponse priorityResponse) {
        Priority priority = priorityRepository.findById(priorityResponse.getPriorityID()).get();

        if (!priorityResponse.getTitle().isEmpty() && !priorityResponse.getTitle().isBlank()) {
            priority.setTitle(priorityResponse.getTitle());
        }

        Optional<Color> colorOptional = colorRepository.findById(priorityResponse.getColorID());
        colorOptional.ifPresent(priority::setColor);

        priority = priorityRepository.save(priority);

        return priority;
    }

    public String deletePriority(Integer priorityId) {
        priorityRepository.deleteById(priorityId);
        return "Priority is deleted successfully!";
    }

}
