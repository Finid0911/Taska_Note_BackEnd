package com.example.TaN02.repository;

import com.example.TaN02.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Extends entity class & the identity variable type of that entity
// Include Crud and Paging+Sorting repository
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("Select u from Users u")
    List<Users> getUsersList();

    @Query("Select count(u) from Users u where u.email = :email and u.password = :password")
    Integer checkUserExist(String email, String password);

    @Query("Select u from Users u where u.email = :email and u.password = :password")
    Users getUserId(String email, String password);

    @Query("Select count(u) from Users u where u.email = :email")
    Integer checkUserEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.userID = :userID")
    Users getUserById(Integer userID);

}
