package cl.injcristianrojas.controllers.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeWebController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("serverTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
