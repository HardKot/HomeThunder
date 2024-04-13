package com.homethunder.homethunder.infrastructure.user;

import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.security.JwtService;
import com.homethunder.homethunder.infrastructure.security.UserDetailsImpl;
import com.homethunder.homethunder.infrastructure.user.dto.AuthForm;
import com.homethunder.homethunder.infrastructure.user.dto.RegistrationForm;
import com.homethunder.homethunder.infrastructure.user.dto.UserDTO;
import com.homethunder.homethunder.useCase.user.UserInteract;
import com.homethunder.homethunder.useCase.user.UserInteractError;
import com.leakyabstractions.result.api.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class SelfController {
    private UserInteract userInteract;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    @GetMapping("/self")
    public String create() {


        return "ok";
    }


}
