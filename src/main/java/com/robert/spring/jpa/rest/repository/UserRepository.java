package com.robert.spring.jpa.rest.repository;

import com.robert.spring.jpa.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE UPPER(u.username) = UPPER(:username)")
    User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE UPPER(u.username) LIKE UPPER(:username)")
    List<User> filterByUsername(@Param("username") String username);

}