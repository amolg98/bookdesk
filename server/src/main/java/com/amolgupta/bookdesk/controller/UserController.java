package com.amolgupta.bookdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.amolgupta.bookdesk.entity.BDRole;
import com.amolgupta.bookdesk.entity.Role;
import com.amolgupta.bookdesk.entity.User;
import com.amolgupta.bookdesk.payload.request.LoginRequest;
import com.amolgupta.bookdesk.payload.request.SignupRequest;
import com.amolgupta.bookdesk.payload.response.JwtResponse;
import com.amolgupta.bookdesk.payload.response.MessageResponse;
import com.amolgupta.bookdesk.respository.RoleRepository;
import com.amolgupta.bookdesk.respository.UserRepository;
import com.amolgupta.bookdesk.security.jwt.JwtUtils;
import com.amolgupta.bookdesk.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class UserController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    // @PostMapping("/login")
    @RequestMapping(method = RequestMethod.POST, value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        System.out.println("username and password is " + loginRequest.getUsername() + " - " + loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetailsImpl.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

        System.out.println("JWT is " + jwt + " " + userDetailsImpl.getId() + " " + userDetailsImpl.getUsername() + " " + userDetailsImpl.getEmail() + " roles are " + roles.toString());

        JwtResponse jwtResponse = new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), userDetailsImpl.getEmail(), roles);
        System.out.println(jwtResponse.toString());

        return ResponseEntity.ok(jwtResponse);
            
    }

    @PostMapping("/signup")
    public ResponseEntity<?> resgisterUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already taken!!"));
        }

        User user = new User(signupRequest.getUsername(),
                                signupRequest.getEmail(),
                                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>(); 

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(BDRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found!!"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch(role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(BDRole.ROLE_ADMIN)
                                            .orElseThrow(() -> new RuntimeException("Error: Role is not found!!"));
                        roles.add(adminRole);
                        break;
                    
                    default:
                        Role userRole = roleRepository.findByName(BDRole.ROLE_USER)
                                            .orElseThrow(() -> new RuntimeException("Error: Role is not found!!"));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!!"));
    }




    // @Autowired
    // UserServiceNew userService;

    // @RequestMapping(method = RequestMethod.GET,
    //         produces = MediaType.APPLICATION_JSON_VALUE)
    // public List<User> findAll() {
    //     System.out.println("In findAll of User");
    //     return userService.findAll();
    // }

    // @RequestMapping(method = RequestMethod.GET, value = "/{id}",
    //         produces = MediaType.APPLICATION_JSON_VALUE)
    // public User findById(@PathVariable("id") String id) {
    //     User user = userService.findById(id);
    //     return user;
    // }

    // @RequestMapping(method = RequestMethod.POST,
    //         consumes=MediaType.APPLICATION_JSON_VALUE,
    //         produces=MediaType.APPLICATION_JSON_VALUE)
    // public void create(@RequestBody User user) {
    //     userService.createUser(user);
    // }

    // @RequestMapping(method = RequestMethod.PUT,
    //         consumes=MediaType.APPLICATION_JSON_VALUE,
    //         produces=MediaType.APPLICATION_JSON_VALUE)
    // public User update(@RequestBody User user) {
    //     return userService.updateUser(user);
    // }

    // @RequestMapping(method = RequestMethod.DELETE,
    //         value = "/{id}")
    // public void delete(@PathVariable("id") String id) {
    //     userService.deleteUser(id);
    // }

}
