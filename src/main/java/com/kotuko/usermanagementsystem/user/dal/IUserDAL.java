package com.kotuko.usermanagementsystem.user.dal;

import com.kotuko.usermanagementsystem.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDAL extends JpaRepository<User, String> {

    User findByUsernameIgnoreCase(String username);

    User findByEmailIgnoreCase(String email);

    User findByFirstNameIgnoreCase(String firstName);

    User findByLastNameIgnoreCase(String firstName);

    @Query("select u from User u where u.email=:email")
    Page<User> findAllEmails(String email,Pageable pageable);


    @Query("select u from User  u where u.firstName=:firstName")
    Page<User> findAllByFirstName(String firstName,Pageable pageable);

    @Query("select u from User  u where u.lastName=:lastName")
    Page<User> findByLastName(String lastName, Pageable pageable);

}
