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
import cl.injcristianrojas.data.jpa.repositories.PostRepository;
import cl.injcristianrojas.data.jpa.repositories.RoleRepository;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private AppUserRepository userRepository;
	
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
		createRoleIfNotFound(ROLE_ADMIN);
		createRoleIfNotFound(ROLE_USER);
		
		createUser("admin", "admin", ROLE_ADMIN);
		createUser("lhamilton", "123", ROLE_USER);
		createUser("mverstappen", "123", ROLE_USER);
		createUser("svettel", "123", ROLE_USER);
		createUser("falonso", "123", ROLE_USER);
		
		createPost("Bienvenidos a Fans de las Aves Chilenas. Soy el administrador.");
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
