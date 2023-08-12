package com.sourabh.ecommerceapp.Service;

import org.springframework.stereotype.Service;

import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.User;

@Service
public interface UserService 
{
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

}
