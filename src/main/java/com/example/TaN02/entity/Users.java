package com.example.TaN02.entity;

import com.example.TaN02.response.UsersResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "RoleID")
    private Integer roleID;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Username")
    private String userName;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "Lastlogin")
    private String lastLogin;

    @Column(name = "Registeredtime")
    private String registeredTime;

    @OneToMany(mappedBy = "users")
    private List<Plans> usersPlan;

    public Users(Integer roleID, String email, String password, String userName, String avatar, String lastLogin, String registeredTime) {
        this.roleID = roleID;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.avatar = avatar;
        this.lastLogin = lastLogin;
        this.registeredTime = registeredTime;
    }

    public Users(UsersResponse usersResponse) {
        this.roleID = usersResponse.getRoleID();
        this.email = usersResponse.getEmail();
        this.password = usersResponse.getPassword();
        this.userName = usersResponse.getUserName();
        this.avatar = usersResponse.getAvatar();
        this.lastLogin = usersResponse.getLastLogin();
        this.registeredTime = usersResponse.getRegisteredTime();
    }
}
