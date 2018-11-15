package cl.injcristianrojas.webservice;

import cl.injcristianrojas.data.model.Post;
import cl.injcristianrojas.data.repositories.PostRepository;
import cl.injcristianrojas.data.repositories.UserRepository;
import cl.injcristianrojas.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PostRepository postRepo;

    @GetMapping("/api/users")
    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/api/posts")
    public List<Post> retrieveAllPosts() {
        return postRepo.findAll();
    }

}
