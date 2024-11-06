package com.example.TaN02.service;

import com.example.TaN02.entity.Color;
import com.example.TaN02.entity.Priority;
import com.example.TaN02.entity.Status;
import com.example.TaN02.entity.Type;
import com.example.TaN02.repository.ColorRepository;
import com.example.TaN02.repository.PriorityRepository;
import com.example.TaN02.repository.StatusRepository;
import com.example.TaN02.repository.TypeRepository;
import com.example.TaN02.response.ColorResponse;
import com.example.TaN02.response.PriorityResponse;
import com.example.TaN02.response.StatusResponse;
import com.example.TaN02.response.TypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    StatusRepository statusRepository;

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Optional<Color> getColorById(Integer colorId) {
        return colorRepository.findById(colorId);
    }

    public Color getColorById1(Integer iconId) {
        return colorRepository.getColorById(iconId);
    }

    public Color addNewColor(ColorResponse colorResponse) {
        Color color = new Color(colorResponse);

        color = colorRepository.save(color);

        // Add priority have that color
        List<Priority> priorityList = new ArrayList<>();
        if (colorResponse.getPriorityColor() != null) {
            for (PriorityResponse priorityResponse: colorResponse.getPriorityColor()) {
                Priority priority = new Priority();
                priority.setTitle(priorityResponse.getTitle());
                priority.setColor(color);

                priorityList.add(priority);
            }
            priorityRepository.saveAll(priorityList);
        }
        color.setPriorityColor(priorityList);

        // Add type have that color
        List<Type> typeList = new ArrayList<>();
        if (colorResponse.getTypeColor() != null) {
            for (TypeResponse typeResponse: colorResponse.getTypeColor()) {
                Type type = new Type();
                type.setTitle(typeResponse.getTitle());
                type.setColor(color);

                typeList.add(type);
            }
            typeRepository.saveAll(typeList);
        }
        color.setTypeColor(typeList);

        // Add status have that color
        List<Status> statusList = new ArrayList<>();
        if (colorResponse.getStatusColor() != null) {
            for (StatusResponse statusResponse: colorResponse.getStatusColor()) {
                Status status = new Status();
                status.setTitle(statusResponse.getTitle());
                status.setColor(color);

                statusList.add(status);
            }
            statusRepository.saveAll(statusList);
        }
        color.setStatusColor(statusList);

        return color;
    }

    public Color updateColor(ColorResponse colorResponse) {
        Color color = colorRepository.findById(colorResponse.getColorID()).get();

        if (!colorResponse.getColorName().isEmpty() && !colorResponse.getColorName().isBlank()) {
            color.setColorName(colorResponse.getColorName());
        }

        if (!colorResponse.getColorCode().isEmpty() && !colorResponse.getColorCode().isBlank()) {
            color.setColorCode(colorResponse.getColorCode());
        }

        color = colorRepository.save(color);
        return color;
    }

    public String deleteColor(Integer colorId) {
        List<Priority> priorityList = priorityRepository.findPriorityByColorId(colorId);
        if (priorityList.isEmpty()) {
            colorRepository.deleteById(colorId);
            return "Color is deleted successfully!";
        }
        return "Cannot delete this color!";
    }

}
