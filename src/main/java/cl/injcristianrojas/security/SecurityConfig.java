package cl.injcristianrojas.security;

import static cl.injcristianrojas.security.jwt.Constants.SIGN_UP_URL;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import cl.injcristianrojas.data.service.JwtUserDetailsService;
import cl.injcristianrojas.data.service.MainUserDetailsService;
import cl.injcristianrojas.security.jwt.JwtAuthenticationFilter;
import cl.injcristianrojas.security.jwt.JwtAuthorizationFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig {

	@Configuration
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private WebApplicationContext applicationContext;
		private MainUserDetailsService userDetailsService;
		@Autowired
		private DataSource dataSource;

		@PostConstruct
		public void completeSetup() {
			userDetailsService = applicationContext.getBean(MainUserDetailsService.class);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.and().csrf().disable();
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/css/**", "/images/**", "/h2/**", "/error");
		}

		@Override
		protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(encoder()).and()
					.authenticationProvider(authenticationProvider()).jdbcAuthentication().dataSource(dataSource);
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

	@Order(1)
	@Configuration
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		private JwtUserDetailsService userDetailsService;
		private PasswordEncoder passwordEncoder;

		public ApiSecurityConfig(JwtUserDetailsService userDetailsService) {
			this.userDetailsService = userDetailsService;
			this.passwordEncoder = NoOpPasswordEncoder.getInstance();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.antMatcher("/api/**")
					.cors().and().csrf().disable()
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
					.anyRequest().authenticated()
					.and()
					.addFilter(new JwtAuthenticationFilter(authenticationManager()))
					.addFilter(new JwtAuthorizationFilter(authenticationManager()))
					// this disables session creation on Spring Security
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		}

		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
			return source;
		}

	}

}