package cl.injcristianrojas.data.jpa.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.injcristianrojas.data.jpa.model.AppUser;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;

import static java.util.Collections.emptyList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private AppUserRepository applicationUserRepository;

    public JwtUserDetailsService(AppUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
	
}
