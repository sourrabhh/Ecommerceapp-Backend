package com.sourabh.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.Model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByEmail(String email);
}
