package com.homethunder.homethunder.infrastructure.user;

import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.libs.CookieLibs;
import com.homethunder.homethunder.infrastructure.security.JwtService;
import com.homethunder.homethunder.infrastructure.security.UserDetailsImpl;
import com.homethunder.homethunder.infrastructure.user.dto.AuthForm;
import com.homethunder.homethunder.infrastructure.user.dto.RegistrationForm;
import com.homethunder.homethunder.infrastructure.user.dto.UserDTO;
import com.homethunder.homethunder.useCase.user.UserInteract;
import com.homethunder.homethunder.useCase.user.UserInteractError;
import com.leakyabstractions.result.api.Result;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@AllArgsConstructor
@RequestMapping
@CrossOrigin
public class AuthController {
    private UserInteract userInteract;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    @PostMapping("/registration")
    public ResponseEntity<?> create(@Valid @RequestBody RegistrationForm body, HttpServletResponse response, @RequestHeader("user-agent") String userAgent) {
        Result<User, UserInteractError> result = userInteract.registration(body);
        if (result.hasFailure()) return ResponseEntity.status(403).body(result.getFailure().get());
        User user = result.getSuccess().get();

        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        response.addCookie(CookieLibs.setCookieAuth(jwtService.generateToken(userDetails, userAgent, true), true));
        response.setContentType("text/plain");

        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthForm body, HttpServletResponse response, @RequestHeader("user-agent") String userAgent) {
        try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.email(), body.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()));
        }
        User user = userRepository.findByEmail(body.email()).get().toUser();
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        response.addCookie(CookieLibs.setCookieAuth(jwtService.generateToken(userDetails, userAgent, body.rememberMe()), body.rememberMe()));
        response.setContentType("text/plain");

        return ResponseEntity.ok(new UserDTO(user));
    }
}
