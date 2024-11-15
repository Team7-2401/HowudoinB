package edu.sabanciuniv.howudoinb.repository;

import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.model.FriendModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface UserRepository extends MongoRepository<UserModel, String> {
    List<UserModel> findByEmail(String email);

    @Query("{ 'email': ?0 }")
    @Update("{ '$set': { 'friends': ?1 } }")
    void updateFriendsByEmail(String email, List<FriendModel> friends);


    @Query("{ 'email': ?0, 'friends.email': ?1, 'friends.status': { '$in': ['waiting', 'pending'] } }")
    @Update("{ '$set': { 'friends.$.status': 'accepted' } }")
    void updateFriendStatusToAccepted(String email, String friendEmail);


}
