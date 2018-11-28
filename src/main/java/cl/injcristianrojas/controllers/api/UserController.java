package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.model.User;
import cl.injcristianrojas.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/api/users")
    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }

}
