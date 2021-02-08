package com.surf_test.calculator.data.repository;

import com.surf_test.calculator.data.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface UserRepository extends PagingAndSortingRepository<User, UUID> {


    @Query("select u from User u where u.name = ?1")
    User findByName(String name);


    @Query("select u from User u where u.userId = ?1")
    User findById(String id);

}
