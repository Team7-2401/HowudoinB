package edu.sabanciuniv.howudoinb.repository;

import edu.sabanciuniv.howudoinb.model.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<MessageModel, String> {
    List<MessageModel> findByReceivers_EmailAndSender_Email(String receiver, String email);
}
