package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jdbc.model.PostJDBC;
import cl.injcristianrojas.data.jdbc.service.PostServiceJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostRestController {

    @Autowired
    private PostServiceJDBC postService;

    @GetMapping("/api/v1/posts")
    public List<PostJDBC> retrieveAllPosts() {
        return postService.findAll();
    }

    @PostMapping("/api/v1/posts/add")
    public PostJDBC newPost(@RequestBody PostJDBC newPost){
        return postService.save(newPost);
    }
}
