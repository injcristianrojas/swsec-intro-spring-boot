package cl.injcristianrojas.controllers.web;

import cl.injcristianrojas.data.model.Post;
import cl.injcristianrojas.data.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @Autowired
    private PostRepository repo;

    @PostMapping("/posts")
    public String createPost(@RequestParam("message") String message, Model model) {
    	Post post = new Post();
    	post.setMessage(message);
    	repo.save(post);
    	model.addAttribute("posts", repo.findAll());
    	return "posts";
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        model.addAttribute("posts", repo.findAll());
        return "posts";
    }
}
