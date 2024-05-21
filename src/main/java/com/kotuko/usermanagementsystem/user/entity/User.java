package com.kotuko.usermanagementsystem.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kotuko.usermanagementsystem.config.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false, length = 50,name = "username")
    private String username;
    @Column(unique = true, nullable = false, length = 50,name = "first_name")
    private String firstName;
    @Column(unique = true, nullable = false, length = 50,name = "last_name")
    private String lastName;
    @Column(unique = true, nullable = false, length = 50,name = "email")
    private String email;
    @Column(unique = true, nullable = false, length = 50,name = "date_of_birth")
    private String dateOfBirth;

}
