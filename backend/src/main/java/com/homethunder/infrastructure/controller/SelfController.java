package com.homethunder.infrastructure.controller;

import com.homethunder.domain.user.User;
import com.homethunder.infrastructure.db.schema.UserSchema;
import com.homethunder.infrastructure.security.AuthenticationFacade;
import com.homethunder.infrastructure.user.dto.UserDTO;
import com.homethunder.infrastructure.user.dto.UserNewPasswordForm;
import com.homethunder.infrastructure.user.dto.UserUpdateForm;
import com.homethunder.useCase.user.UserInteract;
import com.homethunder.useCase.user.UserInteractError;
import com.leakyabstractions.result.api.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/self")
public class SelfController {
    private UserInteract userInteract;
    private AuthenticationFacade authenticationFacade;

    @GetMapping
    public ResponseEntity<?> get() {
        UserSchema userSchema = authenticationFacade.getAuthentication();
        if (userSchema == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDTO(userSchema.toUser()));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdateForm form) {
        UserSchema userSchema = authenticationFacade.getAuthentication();
        Result<User, UserInteractError> result = userInteract.update(userSchema.toUser(), form);
        if (result.hasFailure()) return ResponseEntity.status(403).body(result.getFailure().get());
        User user = result.getSuccess().get();

        return ResponseEntity.ok(new UserDTO(user));
    }
    @DeleteMapping
    public ResponseEntity<?> delete() {
        UserSchema userSchema = authenticationFacade.getAuthentication();
        Result<User, UserInteractError> result = userInteract.selfDelete(userSchema.toUser());
        if (result.hasFailure()) return ResponseEntity.status(403).body(result.getFailure().get());
        User user = result.getSuccess().get();

        return ResponseEntity.ok(new UserDTO(user));
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UserNewPasswordForm form) {
        UserSchema userSchema = authenticationFacade.getAuthentication();
        Result<User, UserInteractError> result = userInteract.changePassword(userSchema.toUser(), form);
        if (result.hasFailure()) return ResponseEntity.status(403).body(result.getFailure().get());
        User user = result.getSuccess().get();

        return ResponseEntity.ok(new UserDTO(user));
    }
}
