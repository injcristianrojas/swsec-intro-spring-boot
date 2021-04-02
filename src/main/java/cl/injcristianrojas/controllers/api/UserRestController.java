package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jpa.model.User;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private AppUserRepository userRepo;

    @GetMapping("/api/users")
    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/api/users/user/{username}")
    public List<User> showUsersByUserName(@PathVariable String username) {
        return userRepo.getUsersByUsername(username);
    }

}
