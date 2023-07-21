package cl.injcristianrojas.controllers.api;

import cl.injcristianrojas.data.jdbc.model.UserJDBC;
import cl.injcristianrojas.data.jdbc.service.UserServiceJDBC;
import cl.injcristianrojas.data.jpa.model.UserJPA;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepositoryJPA;
import cl.injcristianrojas.data.jpa.service.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceJDBC userServiceJDBC;
    @Autowired
    private AppUserRepositoryJPA userRepoJPA;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    private boolean isAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername()
                : principal.toString();
        UserDetails details = userDetailsService.loadUserByUsername(username);
        return (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @GetMapping("/api/v1/users")
    public List<UserJPA> retrieveAllUsers() {
        return userRepoJPA.findAll();
    }

    @GetMapping("/api/v1/users/user/{username}")
    public List<UserJPA> retrieveUsersByUserName(@PathVariable String username) {
        return userRepoJPA.getUsersByUsername(username);
    }

    @GetMapping("/api/v2/users")
    public List<UserJDBC> getAllUsers() {
        return isAdmin() ? userServiceJDBC.findAll() : null;
    }

    @GetMapping("/api/v2/users/user/{username}")
    public List<UserJDBC> getUsersByUserName(@PathVariable String username) {
        return userServiceJDBC.getUsersByUsername(username);
    }

}
