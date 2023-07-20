package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.model.Post;
import cl.injcristianrojas.data.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostRestController {

    @Autowired
    private PostRepository postRepo;

    @GetMapping("/posts")
    public List<Post> retrieveAllPosts() {
        return postRepo.findAll();
    }

    @PostMapping("/posts")
    Post newPost(@RequestBody Post newPost){
        return postRepo.save(newPost);
    }
}
