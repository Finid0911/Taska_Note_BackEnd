package com.example.TaN02.service;

import com.example.TaN02.entity.Icon;
import com.example.TaN02.entity.Plans;
import com.example.TaN02.entity.Users;
import com.example.TaN02.repository.PlansRepository;
import com.example.TaN02.repository.UsersRepository;
import com.example.TaN02.response.PlansResponse;
import com.example.TaN02.response.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PlansRepository plansRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public List<Users> getUsersListMock() {
        return usersRepository.getUsersList();
    }

    public Users findUserId(String email, String password) {
        return usersRepository.getUserId(email, password);
    }

    public Integer checkIfUserExist(String email, String password) {
        return usersRepository.checkUserExist(email, password);
    }

    public Integer checkUserEmail(String email) {
        return usersRepository.checkUserEmail(email);
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public Users getUserByUserId(Integer userId) {
        return usersRepository.getUserById(userId);
    }

    public Users addNewUser(UsersResponse usersResponse) {
        Users user = new Users(usersResponse);
        user = usersRepository.save(user);

        List<Plans> plansList = new ArrayList<>();
        if(usersResponse.getUsersPlan() != null) {
            for (PlansResponse plansResponse: usersResponse.getUsersPlan()) {
                Plans plans = new Plans();
                plans.setTitle(plansResponse.getTitle());
                plans.setDescription(plansResponse.getDescription());
                plans.setCover(plansResponse.getCover());
                plans.setNumOfTask(plansResponse.getNumOfTask());
                plans.setIsFavorite(plansResponse.getIsFavorite());
                plans.setUsers(user);
                plans.setIcon(new Icon());

                plansList.add(plans);
            }
            plansRepository.saveAll(plansList);
        }
        user.setUsersPlan(plansList);

        return user;
    }

    public Users updateUser(UsersResponse usersResponse) {
        Users users = usersRepository.findById(usersResponse.getUserID()).get();

        if (!usersResponse.getUserName().isEmpty() && !usersResponse.getUserName().isBlank()) {
            users.setUserName(usersResponse.getUserName());
        }

        if (!usersResponse.getPassword().isEmpty() && !usersResponse.getPassword().isBlank()) {
            users.setPassword(usersResponse.getPassword());
        }

        if (!usersResponse.getAvatar().equals(users.getAvatar())) {
            users.setAvatar(usersResponse.getAvatar());
        }

        users = usersRepository.save(users);
        return users;
    }

    public String deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
        return "User is deleted successfully!";
    }
}
