package cl.injcristianrojas.data.service;

import cl.injcristianrojas.data.repositories.ApplicationUserRepository;
import cl.injcristianrojas.data.model.ApplicationUser;
import cl.injcristianrojas.security.MainUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MainUserDetailsService implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new MainUserPrincipal(user);
    }
}
