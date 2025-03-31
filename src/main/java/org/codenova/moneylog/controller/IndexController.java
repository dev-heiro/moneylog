package org.codenova.moneylog.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String indexHandle() {
        return "index";
    }
}
