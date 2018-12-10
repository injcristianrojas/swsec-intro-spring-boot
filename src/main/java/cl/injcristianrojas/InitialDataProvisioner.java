package cl.injcristianrojas;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cl.injcristianrojas.data.model.Post;
import cl.injcristianrojas.data.model.Role;
import cl.injcristianrojas.data.model.ApplicationUser;
import cl.injcristianrojas.data.repositories.PostRepository;
import cl.injcristianrojas.data.repositories.RoleRepository;
import cl.injcristianrojas.data.repositories.ApplicationUserRepository;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private ApplicationUserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		
		createUser("admin", "admin", "ROLE_ADMIN");
		createUser("jperez", "123", "ROLE_USER");
		createUser("jbolsonaro", "j", "ROLE_USER");
		createUser("dtrump", "great", "ROLE_USER");
		
		createPost("Holi");
		createPost("Andai soli");
	}

	private void createPost(String message) {
		Post post = new Post();
		post.setMessage(message);
		postRepository.save(post);
	}

	private void createUser(String username, String password, String rolename) {
		ApplicationUser user = new ApplicationUser();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(roleRepository.findByRolename(rolename));
		userRepository.save(user);
		alreadySetup = true;
	}

	@Transactional
	private Role createRoleIfNotFound(String name) {
		Role role = roleRepository.findByRolename(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}

}
