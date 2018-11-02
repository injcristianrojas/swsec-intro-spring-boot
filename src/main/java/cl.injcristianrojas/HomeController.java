package cl.injcristianrojas;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("serverTime", "test" );
        return "home";
    }

}
