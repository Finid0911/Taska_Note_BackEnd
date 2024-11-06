package com.example.TaN02.repository;

import com.example.TaN02.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Integer> {

    @Query("SELECT p FROM Priority p WHERE p.color.colorID = :colorId")
    List<Priority> findPriorityByColorId(Integer colorId);

    @Query("SELECT p FROM Priority p WHERE priorityID = :priorityID")
    Priority getPriorityById(Integer priorityID);

}
