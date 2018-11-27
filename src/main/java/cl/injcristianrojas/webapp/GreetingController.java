package cl.injcristianrojas.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class GreetingController {

    @PostMapping("/greeting")
    public String sayHello(@RequestParam("person") String person, Model model) {
        model.addAttribute("serverTime", ZonedDateTime.now(ZoneId.of("America/Santiago")));
        model.addAttribute("person", person);
        return "greeting";
    }

    @GetMapping("/greeting")
    public String getName(Model model) {
        return "greeting";
    }

}
