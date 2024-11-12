package edu.sabanciuniv.howudoinb.repository;

import edu.sabanciuniv.howudoinb.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserModel, String> {
    List<UserModel> findByEmail(String email);
}
