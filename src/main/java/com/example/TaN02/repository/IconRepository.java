package com.example.TaN02.repository;

import com.example.TaN02.entity.Icon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IconRepository extends JpaRepository<Icon, Integer> {

    @Query("SELECT i FROM Icon i WHERE i.iconID = :iconId")
    Icon getIconById(Integer iconId);
}
