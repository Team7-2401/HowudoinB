package edu.sabanciuniv.howudoinb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GroupModel{
    private UserModel createdby;
    private String groupname;
    private LocalDateTime timestamp;
    private String about;
    private List<UserModel>members;
    private int groupid;
}