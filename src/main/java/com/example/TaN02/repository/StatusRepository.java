package com.example.TaN02.repository;

import com.example.TaN02.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query("SELECT s FROM Status s WHERE s.statusID = :statusId")
    Status getStatusById(Integer statusId);

}
