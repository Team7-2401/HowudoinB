package edu.sabanciuniv.howudoinb.repository;

import edu.sabanciuniv.howudoinb.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
}
