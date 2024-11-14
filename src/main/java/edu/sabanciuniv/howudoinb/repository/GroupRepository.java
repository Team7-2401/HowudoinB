package edu.sabanciuniv.howudoinb.repository;

import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;


public interface GroupRepository extends MongoRepository<GroupModel, String> {
    GroupModel findByGroupid(int groupId);

    @Query("{ 'groupid': ?0 }")
    @Update("{ '$set': { 'members': ?1 } }")
    void updateMembersByGroupid(int groupId, List<UserModel> members);
}
