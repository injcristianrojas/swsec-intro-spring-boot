package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jdbc.model.PostJDBC;
import cl.injcristianrojas.data.jdbc.service.PostServiceJDBC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostRestController {

    @Autowired
    private PostServiceJDBC postServiceJDBC;

    @GetMapping("/api/v2/posts")
    @Operation(summary = "View posts", security = @SecurityRequirement(name = "bearerAuth"))
    public List<PostJDBC> retrieveAllPosts() {
        return postServiceJDBC.findAll();
    }

    @PostMapping("/api/v2/posts/add")
    @Operation(summary = "Add post", security = @SecurityRequirement(name = "bearerAuth"))
    public PostJDBC newPost(@RequestBody PostJDBC newPost){
        return postServiceJDBC.save(newPost);
    }
}
