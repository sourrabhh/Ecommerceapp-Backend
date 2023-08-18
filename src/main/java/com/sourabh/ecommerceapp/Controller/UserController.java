package com.sourabh.ecommerceapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController 
{
    @Autowired
    private UserService userService; 

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException
    {
        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
