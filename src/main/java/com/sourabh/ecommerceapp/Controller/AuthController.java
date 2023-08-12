package com.sourabh.ecommerceapp.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.ecommerceapp.Config.JwtProvider;
import com.sourabh.ecommerceapp.Exceptions.UserException;
import com.sourabh.ecommerceapp.Model.User;
import com.sourabh.ecommerceapp.Repositories.UserRepository;
import com.sourabh.ecommerceapp.Request.LoginRequest;
import com.sourabh.ecommerceapp.Response.AuthResponse;
import com.sourabh.ecommerceapp.Service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
    private UserRepository userRepository;
    private CustomUserServiceImplementation customUserServiceImplementation;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    

    public AuthController(UserRepository userRepository,
            CustomUserServiceImplementation customUserServiceImplementation, JwtProvider jwtProvider,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customUserServiceImplementation = customUserServiceImplementation;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException
    {
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        User isEmailExist = userRepository.findByEmail(email);
        if(isEmailExist != null)
        {
            throw new UserException("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User saveUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(), saveUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Sign Up Success");
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest)
    {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("SignIn Success");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    
    }

    private Authentication authenticate(String username, String password) 
    {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid Username...");
        }

        if( !passwordEncoder.matches(password, userDetails.getPassword()) ){
            throw new BadCredentialsException("Invalid Password...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
