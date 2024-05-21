package com.kotuko.usermanagementsystem.user.dal;

import com.kotuko.usermanagementsystem.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDAL extends JpaRepository<Role, String> {
    Role findByName(String name);
}
