package cl.injcristianrojas;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cl.injcristianrojas.data.jpa.model.PostJPA;
import cl.injcristianrojas.data.jpa.model.RoleJPA;
import cl.injcristianrojas.data.jpa.model.UserJPA;
import cl.injcristianrojas.data.jpa.repositories.PostRepositoryJPA;
import cl.injcristianrojas.data.jpa.repositories.RoleRepositoryJPA;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepositoryJPA;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private AppUserRepositoryJPA userRepository;
	
	@Autowired
	private RoleRepositoryJPA roleRepository;
	
	@Autowired
	private PostRepositoryJPA postRepository;
	
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
		PostJPA post = new PostJPA();
		post.setMessage(message);
		postRepository.save(post);
	}

	private void createUser(String username, String password, String rolename) {
		UserJPA user = new UserJPA();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(roleRepository.findByRolename(rolename));
		userRepository.save(user);
		alreadySetup = true;
	}

	@Transactional
	private RoleJPA createRoleIfNotFound(String name) {
		RoleJPA role = roleRepository.findByRolename(name);
		if (role == null) {
			role = new RoleJPA();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}

}
