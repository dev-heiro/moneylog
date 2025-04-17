package org.codenova.moneylog.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.codenova.moneylog.entity.User;
import org.codenova.moneylog.repository.UserRepository;
import org.codenova.moneylog.request.AvailableCheckRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@Controller
@AllArgsConstructor
public class AuthApiController {

    private UserRepository userRepository;

    @GetMapping("/available")
    @ResponseBody
    public String availableHandle(@ModelAttribute @Valid AvailableCheckRequest req,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "not-email";
        }
        User found =userRepository.findByEmail(req.getEmail());
        if(found != null) {
            return "disable";
        }else {
            return "available";
        }
    }
}
