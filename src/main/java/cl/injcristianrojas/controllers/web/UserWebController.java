package cl.injcristianrojas.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.injcristianrojas.data.repositories.ApplicationUserRepository;

@Controller
public class UserWebController {

    @Autowired
    private ApplicationUserRepository userRepo;

    @GetMapping("/users")
    public String showUsers(Model model) {
    	model.addAttribute("users", userRepo.findAll());
        return "users";
    }
    
    @GetMapping("/users/role/{roleid}")
    public String showUsersByRoleId(Model model, @PathVariable String roleid) {
    	model.addAttribute("users", userRepo.getUsersByRoleId(roleid));
        return "users";
    }

}
