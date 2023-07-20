package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jpa.model.AppUser;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    private AppUserRepository userRepo;

    @GetMapping("/users")
    public List<AppUser> retrieveAllUsers() {
        return userRepo.findAll();
    }

}
