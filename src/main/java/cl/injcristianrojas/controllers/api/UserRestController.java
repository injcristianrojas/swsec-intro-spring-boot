package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jpa.model.UserJPA;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private AppUserRepository userRepo;

    @GetMapping("/api/v1/users")
    public List<UserJPA> retrieveAllUsers() {
        return userRepo.findAll();
    }

}
