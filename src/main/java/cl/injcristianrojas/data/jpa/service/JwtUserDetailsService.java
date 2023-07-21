package cl.injcristianrojas.data.jpa.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.injcristianrojas.data.jpa.model.UserJPA;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepositoryJPA;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private AppUserRepositoryJPA applicationUserRepository;

    public JwtUserDetailsService(AppUserRepositoryJPA applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJPA applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = new User(applicationUser.getUsername(), applicationUser.getPassword(), applicationUser.getAuthorities());
        System.out.println("============================ USER DETAILS ====================================");
        System.out.println(user);
        return user;
    }
	
}
