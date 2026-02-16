package com.example.crudwithsql.Auth;


import com.example.crudwithsql.Security.JwtService;
import com.example.crudwithsql.entity.AppUser;
import com.example.crudwithsql.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class authController {
    @Autowired
    private userRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public String register(@RequestBody AppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        return "User Registerd Successfully!";
    }


    @PostMapping("/login")
    public loginResponse login(@RequestBody loginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        String token = jwtService.generateToken(request.getUsername());
        return new loginResponse(token);
    }


}
