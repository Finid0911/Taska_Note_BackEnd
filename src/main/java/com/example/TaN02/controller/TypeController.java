package com.example.TaN02.controller;

import com.example.TaN02.entity.Type;
import com.example.TaN02.response.TypeResponse;
import com.example.TaN02.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/getAllTypes")
    public List<TypeResponse> getAllTypes() {
        List<Type> typeList = typeService.getAllTypes();
        List<TypeResponse> typeResponseList = new ArrayList<>();

        typeList.forEach(type -> {
            typeResponseList.add(new TypeResponse(type));
        });

        return typeResponseList;
    }

    @GetMapping
    public List<TypeResponse> getTypeById(@RequestParam Integer typeId) {
        Optional<Type> typeOptional = typeService.getTypeById(typeId);
        List<TypeResponse> typeResponseList = new ArrayList<>();

        typeOptional.ifPresent(type -> {
            typeResponseList.add(new TypeResponse(type));
        });

        return typeResponseList;
    }

    @GetMapping("/getTypeById/{typeId}")
    public TypeResponse getTypeById1(@PathVariable("typeId") Integer typeId) {
        Type type = typeService.getTypeById1(typeId);

        return new TypeResponse(type);
    }

    @PostMapping("/addNewType")
    public TypeResponse addNewType(@Valid @RequestBody TypeResponse typeResponse) {
        Type type = typeService.addNewType(typeResponse);

        return new TypeResponse(type);
    }

    @PutMapping("/updateType")
    public TypeResponse updateType(@Valid @RequestBody TypeResponse typeResponse) {
        Type type = typeService.updateType(typeResponse);

        return new TypeResponse(type);
    }

    @DeleteMapping("/deleteType")
    public String deleteType(@RequestParam Integer typeId) {
        return typeService.deleteType(typeId);
    }
}
