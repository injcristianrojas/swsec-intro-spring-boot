package cl.injcristianrojas.controllers.api;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.model.ApplicationUser;
import cl.injcristianrojas.data.repositories.ApplicationUserRepository;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("/api")
public class ApplicationUserController {

	private ApplicationUserRepository applicationUserRepository;
	private PasswordEncoder passwordEncoder;

	public ApplicationUserController(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
		this.passwordEncoder = NoOpPasswordEncoder.getInstance();
	}
	
	@PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

}
