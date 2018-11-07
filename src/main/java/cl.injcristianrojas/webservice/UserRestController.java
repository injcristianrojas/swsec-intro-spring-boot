package cl.injcristianrojas.webservice;

import cl.injcristianrojas.data.UserRepository;
import cl.injcristianrojas.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/api/users")
    public List<User> retrieveAllUsers() {
        return repo.findAll();
    }

}
