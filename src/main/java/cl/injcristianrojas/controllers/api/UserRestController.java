package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.model.ApplicationUser;
import cl.injcristianrojas.data.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private ApplicationUserRepository userRepo;

    @GetMapping("/api/users")
    public List<ApplicationUser> retrieveAllUsers() {
        return userRepo.findAll();
    }

}
