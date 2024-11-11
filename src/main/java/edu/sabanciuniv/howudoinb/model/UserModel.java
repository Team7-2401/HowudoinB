package edu.sabanciuniv.howudoinb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserModel{
    private String email;
    private String password;
    private String lastname;
    private String name;
    private List<Map<UserModel,String>>friends;
    private String aboutme;
}