package edu.sabanciuniv.howudoinb.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter @Setter
@Document(collection = "Groups")
public class GroupModel{
    private UserModel createdby;
    private String groupname;
    private LocalDateTime timestamp;
    private String about;
    private List<UserModel>members;
    private int groupid;

    public int validateCreate(){
        //createdby will be set later
        //validate groupname
        if (groupname == null || groupname.isEmpty()){
            return 1;
        }
        //set timestamp
        this.timestamp = LocalDateTime.now();

        //generate groupid (from 10000, to 99999)
        this.groupid = (int)(Math.random() * 90000) + 10000;

        return 0;
    }
}

