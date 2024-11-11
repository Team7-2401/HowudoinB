package edu.sabanciuniv.howudoinb.repository;

import edu.sabanciuniv.howudoinb.model.GroupModel;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GroupRepository extends MongoRepository<Group, String> {
}
