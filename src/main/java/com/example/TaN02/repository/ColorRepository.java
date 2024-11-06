package com.example.TaN02.repository;

import com.example.TaN02.entity.Color;
import com.example.TaN02.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

    @Query("SELECT c FROM Color c WHERE c.colorID = :colorId")
    Color getColorById(Integer colorId);

}
