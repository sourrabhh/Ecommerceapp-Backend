package com.sourabh.ecommerceapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.sourabh.ecommerceapp.Model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByEmail(String email);
}
