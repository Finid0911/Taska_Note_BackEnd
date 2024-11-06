package com.example.TaN02.controller;

import com.example.TaN02.entity.Icon;
import com.example.TaN02.response.IconResponse;
import com.example.TaN02.service.IconService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/icon")
public class IconController {

    @Autowired
    IconService iconService;

    @GetMapping("/getAllIcons")
    public List<IconResponse> getAllIcons() {
        List<Icon> iconList = iconService.getAllIcons();
        List<IconResponse> iconResponseList = new ArrayList<>();

        iconList.forEach(icon -> {
            iconResponseList.add(new IconResponse(icon));
        });
        return iconResponseList;
    }

    @GetMapping
    public List<IconResponse> getIconById(@RequestParam Integer iconId) {
        Optional<Icon> optionalIcon = iconService.getIconById(iconId);
        List<IconResponse> iconResponseList = new ArrayList<>();

        optionalIcon.ifPresent(icon -> {
            iconResponseList.add(new IconResponse(optionalIcon.get()));
        });
        return iconResponseList;
    }

    @GetMapping("/getIconById/{iconId}")
    public IconResponse getIconById1(@PathVariable("iconId") Integer iconId) {
        Icon icon = iconService.getIconById1(iconId);

        return new IconResponse(icon);
    }

    @PostMapping("/addNewIcon")
    public IconResponse addNewIcon(@Valid @RequestBody IconResponse iconResponse) {
        Icon icon = iconService.addNewIcon(iconResponse);

        return new IconResponse(icon);
    }

    @PutMapping("/updateIcon")
    public IconResponse updateIcon(@Valid @RequestBody IconResponse iconResponse) {
        Icon icon = iconService.updateIcon(iconResponse);

        return new IconResponse(icon);
    }

    @DeleteMapping("/deleteIcon")
    public String deleteIcon(@RequestParam Integer iconId) {
        return iconService.deleteIcon(iconId);
    }

}
