package edu.sabanciuniv.howudoinb.repository;

import edu.sabanciuniv.howudoinb.model.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageModel, String> {
}
