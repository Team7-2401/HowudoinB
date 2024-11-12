package edu.sabanciuniv.howudoinb.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Setter @Getter
@Document(collection = "Users")
public class UserModel{

    protected String name;
    protected String lastName;
    protected String email;
    protected String password;
    private ArrayList<FriendModel> friends;
    protected String aboutme;

    public int validateUser(){
        if(this.name == null || this.name.isEmpty()){
            return 1;
        }
        if(this.lastName == null || this.lastName.isEmpty()){
            return 2;
        }
        if(this.email == null || this.email.isEmpty()){
            return 3;
        }
        //check email format with regex
        else if(!this.email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
            return 4;
        }
        if(this.password == null || this.password.isEmpty() || this.password.length() < 8){
            return 5;
        }
        return 0;
    }
}