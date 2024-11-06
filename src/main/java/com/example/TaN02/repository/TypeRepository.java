package com.example.TaN02.repository;

import com.example.TaN02.entity.Status;
import com.example.TaN02.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    @Query("SELECT s FROM Type s WHERE s.typeID = :typeId")
    Type getTypeById(Integer typeId);

}
