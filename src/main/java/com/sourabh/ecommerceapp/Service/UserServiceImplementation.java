package com.sourabh.ecommerceapp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.ecommerceapp.Config.JwtProvider;
import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException 
    {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
        {   
            return user.get();
        }

        throw new UserException("User Not Found with ID "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException 
    {
        String email = jwtProvider.getEmailFromToken(jwt);
        
        User user = userRepository.findByEmail(email);

        if(user == null)
        {
            throw new UserException("User Not Found with Email "+email);
        }
        return user;
    }
    
}
