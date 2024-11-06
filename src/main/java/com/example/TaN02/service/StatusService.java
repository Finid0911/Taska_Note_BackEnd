package com.example.TaN02.service;

import com.example.TaN02.entity.Color;
import com.example.TaN02.entity.Status;
import com.example.TaN02.repository.ColorRepository;
import com.example.TaN02.repository.StatusRepository;
import com.example.TaN02.response.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    ColorRepository colorRepository;

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Optional<Status> getStatusById(Integer statusId) {
        return statusRepository.findById(statusId);
    }

    public Status getStatusById1(Integer statusId) {
        return statusRepository.getStatusById(statusId);
    }

    public Status addNewStatus(StatusResponse statusResponse) {
        Status status = new Status(statusResponse);

        status = statusRepository.save(status);
        return status;
    }

    public Status updateStatus(StatusResponse statusResponse) {
        Status status = statusRepository.findById(statusResponse.getStatusID()).get();

        if (!statusResponse.getTitle().isEmpty() && !statusResponse.getTitle().isBlank()) {
            status.setTitle(statusResponse.getTitle());
        }

        Optional<Color> colorOptional = colorRepository.findById(statusResponse.getColorID());
        colorOptional.ifPresent(status::setColor);

        status = statusRepository.save(status);

        return status;
    }

    public String deleteStatus(Integer statusId) {
        statusRepository.deleteById(statusId);
        return "Status is deleted successfully!";
    }
}
