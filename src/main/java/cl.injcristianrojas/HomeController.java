package cl.injcristianrojas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("serverTime", ZonedDateTime.now(ZoneId.of("America/Santiago")));
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "template.html";
    }

}
