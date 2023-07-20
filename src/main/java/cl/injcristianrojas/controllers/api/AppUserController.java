package cl.injcristianrojas.controllers.api;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.jpa.model.AppUser;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("/api/v1")
public class AppUserController {

	private AppUserRepository applicationUserRepository;
	private PasswordEncoder passwordEncoder;

	public AppUserController(AppUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
		this.passwordEncoder = NoOpPasswordEncoder.getInstance();
	}
	
	@PostMapping("/sign-up")
    public void signUp(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

}
