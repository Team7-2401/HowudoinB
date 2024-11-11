package edu.sabanciuniv.howudoinb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MessageModel{
    private String content;
    private UserModel sender;
    private List<UserModel>receivers;
    private LocalDateTime timestamp;
    private boolean status;
    private GroupModel groupid;
}