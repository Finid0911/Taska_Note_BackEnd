package com.example.TaN02.service;

import com.example.TaN02.entity.Icon;
import com.example.TaN02.repository.IconRepository;
import com.example.TaN02.response.IconResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IconService {

    @Autowired
    IconRepository iconRepository;

    public List<Icon> getAllIcons() {
        return iconRepository.findAll();
    }

    public Optional<Icon> getIconById(Integer iconId) {
        return iconRepository.findById(iconId);
    }

    public Icon addNewIcon(IconResponse iconResponse) {
        Icon icon = new Icon(iconResponse);

        icon = iconRepository.save(icon);
        return icon;
    }

    public Icon getIconById1(Integer iconId) {
        return iconRepository.getIconById(iconId);
    }


    public Icon updateIcon(IconResponse iconResponse) {
        Icon icon = iconRepository.findById(iconResponse.getIconID()).get();

        if (!iconResponse.getIconName().isEmpty() && !iconResponse.getIconName().isBlank()) {
            icon.setIconName(iconResponse.getIconName());
        }

        //TODO: Need to catch image type (.png, .jpg,...)
        if (!iconResponse.getImage().isEmpty()) {
            icon.setImage(iconResponse.getImage());
        }

        icon = iconRepository.save(icon);
        return icon;
    }

    public String deleteIcon(Integer iconId) {
        iconRepository.deleteById(iconId);
        return "Icon is deleted successfully!";
    }

}
