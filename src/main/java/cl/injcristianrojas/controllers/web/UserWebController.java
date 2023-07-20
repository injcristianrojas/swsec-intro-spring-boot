package cl.injcristianrojas.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;

@Controller
public class UserWebController {

    @Autowired
    private AppUserRepository userRepo;
    private static final String USERS = "users";

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute(USERS, userRepo.findAll());
        return USERS;
    }

    @GetMapping("/users/user/{username}")
    public String showUsersByUserName(Model model, @PathVariable String username) {
        model.addAttribute(USERS, userRepo.getUsersByUsername(username));
        return USERS;
    }

    @GetMapping("/users/type/{type}")
    public String showUsersByRoleId(Model model, @PathVariable String type) {
        model.addAttribute(USERS, userRepo.getUsersByType(type));
        return USERS;
    }

}
