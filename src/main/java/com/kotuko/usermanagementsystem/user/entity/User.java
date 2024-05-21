package com.kotuko.usermanagementsystem.user.entity;

import com.kotuko.usermanagementsystem.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false, length = 50, name = "username")
    private String username;
    @Column( nullable = false, length = 50, name = "first_name")
    private String firstName;
    @Column( nullable = false, length = 50, name = "last_name")
    private String lastName;
    @Column(unique = true, nullable = false, length = 50, name = "email")
    private String email;
    @Column(unique = true, nullable = false, length = 50, name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
