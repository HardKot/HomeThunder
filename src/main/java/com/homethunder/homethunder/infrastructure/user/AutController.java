package com.homethunder.homethunder.infrastructure.user;

import com.homethunder.homethunder.infrastructure.user.dto.RegistrationForm;
import com.homethunder.homethunder.infrastructure.user.dto.UserDTO;
import com.homethunder.homethunder.useCase.user.UserInteract;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping
public class AutController {
    @Autowired
    private UserInteract userInteract;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody RegistrationForm body) {
        var result = userInteract.registration(body);
         if (result.hasSuccess() && result.getSuccess().isPresent()) {
            return new UserDTO(result.getSuccess().get());
        }
        return null;
    }
}
