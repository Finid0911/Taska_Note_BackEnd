package com.example.TaN02.service;

import com.example.TaN02.entity.Color;
import com.example.TaN02.entity.Type;
import com.example.TaN02.repository.ColorRepository;
import com.example.TaN02.repository.TypeRepository;
import com.example.TaN02.response.TypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    ColorRepository colorRepository;

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public Optional<Type> getTypeById(Integer typeId) {
        return typeRepository.findById(typeId);
    }

    public Type getTypeById1(Integer typeId) {
        return typeRepository.getTypeById(typeId);
    }

    public Type addNewType(TypeResponse typeResponse) {
        Type type = new Type(typeResponse);

        type = typeRepository.save(type);
        return type;
    }

    public Type updateType(TypeResponse typeResponse) {
        Type type = typeRepository.findById(typeResponse.getTypeID()).get();

        if (!typeResponse.getTitle().isEmpty() && !typeResponse.getTitle().isBlank()) {
            type.setTitle(typeResponse.getTitle());
        }

        Optional<Color> colorOptional = colorRepository.findById(typeResponse.getColorID());
        colorOptional.ifPresent(type::setColor);

        type = typeRepository.save(type);

        return type;
    }

    public String deleteType(Integer typeId) {
        typeRepository.deleteById(typeId);
        return "Type is deleted successfully!";
    }
}
