package org.codenova.moneylog.controller;

import lombok.Getter;
import org.codenova.moneylog.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String indexHandle(@SessionAttribute("user") Optional<User> user) {

        if(user.isEmpty()) {
            return "index";
        }else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String homeHandle(@SessionAttribute("user") Optional<User> user, Model model) {
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "home";
        }else {
            return "redirect:/index";
        }
    }

}
