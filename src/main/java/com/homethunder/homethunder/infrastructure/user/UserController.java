package com.homethunder.homethunder.infrastructure.user;

import com.homethunder.homethunder.infrastructure.user.dto.RegistrationForm;
import com.homethunder.homethunder.infrastructure.user.dto.UserDTO;
import com.homethunder.homethunder.useCase.user.UserInteract;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class UserController {
    @Setter
    private UserInteract userInteract;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody RegistrationForm body) {
        var result = userInteract.registration(body);
        if (result.hasSuccess()) {
            return new UserDTO(result.getSuccess().get());
        }
        return  null;
    }
}
