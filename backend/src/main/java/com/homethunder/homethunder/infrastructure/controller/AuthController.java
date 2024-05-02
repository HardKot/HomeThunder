package com.homethunder.homethunder.infrastructure.controller;

import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.security.JwtService;
import com.homethunder.homethunder.infrastructure.security.UserDetailsImpl;
import com.homethunder.homethunder.infrastructure.security.UserDetailsServiceImpl;
import com.homethunder.homethunder.infrastructure.user.dto.AuthForm;
import com.homethunder.homethunder.infrastructure.user.dto.RegistrationForm;
import com.homethunder.homethunder.infrastructure.user.dto.UserDTO;
import com.homethunder.homethunder.useCase.user.UserInteract;
import com.homethunder.homethunder.useCase.user.UserInteractError;
import com.leakyabstractions.result.api.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua_parser.Client;
import ua_parser.Parser;

@RestController
@AllArgsConstructor
@RequestMapping
@CrossOrigin
public class AuthController {
    @Autowired
    private UserInteract userInteract;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @PostMapping("/registration")
    public ResponseEntity<?> create(@Valid @RequestBody RegistrationForm body, @RequestHeader("user-agent") String userAgent) {
        Result<User, UserInteractError> result = userInteract.registration(body);
        if (result.hasFailure()) return ResponseEntity.status(403).body(result.getFailure().get());
        User user = result.getSuccess().get();

        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        Parser uaParser = new Parser();
        Client client = uaParser.parse(userAgent);

        return null;
//        return ResponseEntity.ok(new AuthResponse(new UserDTO(user), jwtService.generateToken(userDetails, client.toString())));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthForm body, @RequestHeader(value = "user-agent", required = false) String userAgent) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.email(), body.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        User user = userRepository.findByEmail(body.email()).get().toUser();
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        Parser uaParser = new Parser();
        Client client = uaParser.parse(userAgent);


        return null;
//        return ResponseEntity.ok(new AuthResponse(new UserDTO(user), jwtService.generateToken(userDetails, client.toString())));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> login(@RequestHeader(value = "user-agent", required = false) String userAgent, @RequestHeader("Authorization") String authorizationHeader) {
        String oldToken = authorizationHeader.split(" ")[1];

        if (oldToken == null) return ResponseEntity.badRequest().build();

        Parser uaParser = new Parser();
        Client client = uaParser.parse(userAgent);

        return null;
////        String token = jwtService.regenerateToken(
////            userDetailsServiceImpl.loadUserByUsername(jwtService.extractEmail(oldToken)),
////            oldToken,
////            client.toString()
////        );
////
////        User user = userRepository.findByEmail(jwtService.extractEmail(oldToken)).get().toUser();
//
//        return ResponseEntity.ok(new AuthResponse(new UserDTO(user), token));
    }

    private record AuthResponse(UserDTO user, String token) {
    }
}
