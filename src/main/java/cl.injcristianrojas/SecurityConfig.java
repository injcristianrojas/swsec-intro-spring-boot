package cl.injcristianrojas;

import cl.injcristianrojas.data.service.MainUserDetailsService;
import cl.injcristianrojas.security.AuthenticationSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .cors()
                    .and()
                    .csrf()
                    .disable();
        }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private WebApplicationContext applicationContext;
        private MainUserDetailsService userDetailsService;
        @Autowired
        private AuthenticationSuccessHandlerImpl successHandler;
        @Autowired
        private DataSource dataSource;

        @PostConstruct
        public void completeSetup() {
            userDetailsService = applicationContext.getBean(MainUserDetailsService.class);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/css/**", "/images/**", "/greeting").permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/login").permitAll()
                    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
        }

        @Override
        protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(encoder())
                    .and()
                    .authenticationProvider(authenticationProvider())
                    .jdbcAuthentication()
                    .dataSource(dataSource);
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService);
            authProvider.setPasswordEncoder(encoder());
            return authProvider;
        }

        @Bean
        public PasswordEncoder encoder() {
            return NoOpPasswordEncoder.getInstance();
        }
    }

}
