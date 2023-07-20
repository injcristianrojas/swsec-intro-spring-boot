package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jpa.model.PostJPA;
import cl.injcristianrojas.data.jpa.repositories.PostRepository;
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
    public List<PostJPA> retrieveAllPosts() {
        return postRepo.findAll();
    }

    @PostMapping("/posts")
    PostJPA newPost(@RequestBody PostJPA newPost){
        return postRepo.save(newPost);
    }
}
