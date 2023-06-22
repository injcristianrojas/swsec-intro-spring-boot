package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jdbc.model.UserJDBC;
import cl.injcristianrojas.data.jdbc.service.UserServiceJDBC;
import cl.injcristianrojas.data.jpa.model.UserJPA;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceJDBC userService;
    @Autowired
    private AppUserRepository userRepo;
    
    @GetMapping("/api/v1/users")
    public List<UserJPA> retrieveAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/api/v1/users/user/{username}")
    public List<UserJPA> retrieveUsersByUserName(@PathVariable String username) {
        return userRepo.getUsersByUsername(username);
    }

    @GetMapping("/api/v2/users")
    public List<UserJDBC> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/api/v2/users/user/{username}")
    public List<UserJDBC> getUsersByUserName(@PathVariable String username) {
        return userService.getUsersByUsername(username);
    }


}
