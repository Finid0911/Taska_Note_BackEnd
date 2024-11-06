package com.example.TaN02.response;

import com.example.TaN02.entity.Plans;
import com.example.TaN02.entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersResponse {

    @JsonProperty("user_id")
    private Integer userID;

    @JsonProperty("role_id")
    private Integer roleID;

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Email is not valid!")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Password must not be blank!")
    @Size(min = 6, message = "Password must have at least 6 characters!")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "Username must not be blank!")
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("last_login")
    private String lastLogin;

    @JsonProperty("registered_time")
    private String registeredTime;

    @JsonProperty("plan_users")
    private List<PlansResponse> usersPlan;

    public UsersResponse(Users users) {
        this.userID = users.getUserID();
        this.roleID = users.getRoleID();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.userName = users.getUserName();
        this.avatar = users.getAvatar();
        this.lastLogin = users.getLastLogin();
        this.registeredTime = users.getRegisteredTime();

        if (users.getUsersPlan() != null) {
            usersPlan = new ArrayList<>();
            for (Plans plans : users.getUsersPlan()) {
                usersPlan.add(new PlansResponse(plans));
            }
        }
    }

}
