package edu.sabanciuniv.howudoinb.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Setter @Getter
@Document(collection = "Messages")
public class MessageModel{
    private String content;
    private UserModel sender;
    private List<UserModel>receivers;
    private LocalDateTime timestamp;
    private boolean status;
    private int groupid;

    public int validateSend() {
        //Check if the message is empty
        if (this.content == null || this.content.isEmpty()) {
            return 1;
        }
        //Check if the sender is valid
        if (this.sender == null) {
            return 2;
        }
        if (this.sender.senderValidate() != 0) {
            return this.sender.senderValidate();
        }
        //Check if the receivers are valid
        if (this.receivers == null || this.receivers.isEmpty()) {
            return 4;
        }
        for (UserModel receiver : this.receivers) {
            if (receiver.senderValidate() != 0) {
                return receiver.senderValidate();
            }
        }

        //overwrite the timestamp with server time
        this.timestamp = LocalDateTime.now();

        this.status = true;

        //message, sender and receivers are valid
        return 0;
    }

    public int validateGroup() {
        //check if message is empty
        if (this.content == null || this.content.isEmpty()) {
            return 1;
        }

        //set timestamp and status
        this.timestamp = LocalDateTime.now();
        this.status = true;

        return 0;
    }

}