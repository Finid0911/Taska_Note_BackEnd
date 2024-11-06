package com.example.TaN02.controller;

import com.example.TaN02.entity.Color;
import com.example.TaN02.response.ColorResponse;
import com.example.TaN02.service.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/color")
public class ColorController {

    @Autowired
    ColorService colorService;

    @GetMapping("/getAllColors")
    public List<ColorResponse> getAllColors() {
        List<Color> colorList = colorService.getAllColors();
        List<ColorResponse> colorResponseList = new ArrayList<>();

        colorList.forEach(color -> {
            colorResponseList.add(new ColorResponse(color));
        });
        return colorResponseList;
    }

    @GetMapping
    public List<ColorResponse> getColorById(@RequestParam Integer colorId) {
        Optional<Color> optionalColor = colorService.getColorById(colorId);
        List<ColorResponse> colorResponseList = new ArrayList<>();

        optionalColor.ifPresent(color -> {
            colorResponseList.add(new ColorResponse(color));
        });

        return colorResponseList;
    }

    @GetMapping("/getColorById/{iconId}")
    public ColorResponse getColorById1(@PathVariable("iconId") Integer iconId) {
        Color color = colorService.getColorById1(iconId);
        return new ColorResponse(color);
    }

    @PostMapping("/addNewColor")
    public ColorResponse addNewColor(@Valid @RequestBody ColorResponse colorResponse) {
        Color color = colorService.addNewColor(colorResponse);

        return new ColorResponse(color);
    }

    @PutMapping("/updateColor")
    public ColorResponse updateColor(@Valid @RequestBody ColorResponse colorResponse) {
        Color color = colorService.updateColor(colorResponse);

        return new ColorResponse(color);
    }

    @DeleteMapping("/deleteColor")
    public String deleteColor(@Valid @RequestParam Integer colorId) {
        return colorService.deleteColor(colorId);
    }

}
