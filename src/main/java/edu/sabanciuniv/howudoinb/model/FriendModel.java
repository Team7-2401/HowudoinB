package edu.sabanciuniv.howudoinb.model;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Setter @Getter
public class FriendModel extends UserModel{

    private String status;
}