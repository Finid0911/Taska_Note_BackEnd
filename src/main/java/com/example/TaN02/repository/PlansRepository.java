package com.example.TaN02.repository;

import com.example.TaN02.entity.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepository extends JpaRepository<Plans, Integer> {

    @Query("SELECT p FROM Plans p WHERE p.users.userID = :userID ORDER BY p.createdTime DESC")
    List<Plans> getPlanListByUserId(Integer userID);

    @Query("SELECT p FROM Plans p WHERE p.users.userID = :userID AND p.isFavorite = :isFavorite ORDER BY p.createdTime DESC")
    List<Plans> getPlanListByUserIdAndIsFav(Integer userID, Integer isFavorite);

    @Query("SELECT p FROM Plans p WHERE p.planID = :planID")
    Plans getPlanById(Integer planID);

}
