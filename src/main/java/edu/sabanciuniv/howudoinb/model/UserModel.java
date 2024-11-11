package edu.sabanciuniv.howudoinb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserModel{

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    private List<Map<UserModel,String>>friends;

    private String aboutme;
}