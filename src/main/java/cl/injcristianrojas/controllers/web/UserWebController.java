package cl.injcristianrojas.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.injcristianrojas.data.repositories.UserRepository;

@Controller
public class UserWebController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public String showUsers(Model model) {
    	model.addAttribute("users", userRepo.findAll());
        return "users";
    }

}
