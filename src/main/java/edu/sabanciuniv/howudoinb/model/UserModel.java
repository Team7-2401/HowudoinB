package edu.sabanciuniv.howudoinb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserModel{
    private String email;
    private String password;
    private String username;
    private String name;
    private List<UserModel>friends;
    private String aboutme;
}