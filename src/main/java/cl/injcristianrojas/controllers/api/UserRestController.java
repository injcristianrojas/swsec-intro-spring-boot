package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jdbc.model.UserJDBC;
import cl.injcristianrojas.data.jdbc.service.UserServiceJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceJDBC userService;

    @GetMapping("/api/users")
    public List<UserJDBC> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/api/users/user/{username}")
    public List<UserJDBC> showUsersByUserName(@PathVariable String username) {
        return userService.getUsersByUsername(username);
    }

}
