package cl.injcristianrojas.data.jpa.service;

import cl.injcristianrojas.data.jpa.repositories.AppUserRepository;
import cl.injcristianrojas.data.jpa.model.AppUser;
import cl.injcristianrojas.security.MainUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MainUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new MainUserPrincipal(user);
    }
}
