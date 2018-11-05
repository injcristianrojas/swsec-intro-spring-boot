package cl.injcristianrojas.webservice;

import cl.injcristianrojas.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServiceRestController {

    @RequestMapping("/api/user")
    public User user() {
        return new User("Harry Potter");
    }

}
