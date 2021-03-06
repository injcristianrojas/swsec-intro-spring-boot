package cl.injcristianrojas.controllers.web;

import cl.injcristianrojas.data.jpa.model.PostJPA;
import cl.injcristianrojas.data.jpa.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostWebController {

    private static final String POSTS = "posts";

    @Autowired
    private PostRepository repo;

    @PostMapping("/posts")
    public String createPost(@RequestParam("message") String message, Model model) {
    	PostJPA post = new PostJPA();
    	post.setMessage(message);
    	repo.save(post);
    	model.addAttribute(POSTS, repo.findAll());
    	return POSTS;
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        model.addAttribute(POSTS, repo.findAll());
        return POSTS;
    }
}
