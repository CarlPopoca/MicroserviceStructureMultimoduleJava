package com.microservicio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	  private final AdminServerProperties adminServer;

	  public SecurityConfig(AdminServerProperties adminServer) {
	    this.adminServer = adminServer;
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
	    successHandler.setTargetUrlParameter("redirectTo");
	    successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

	    http.authorizeRequests(
	        (authorizeRequests) -> authorizeRequests.antMatchers(this.adminServer.path("/assets/**")).permitAll() 
	            .antMatchers(this.adminServer.path("/login")).permitAll()
	            .antMatchers("/actuator/health**").permitAll().anyRequest().authenticated() 
	    ).formLogin(
	        (formLogin) -> formLogin.loginPage(this.adminServer.path("/login")).successHandler(successHandler).and() 
	    ).logout((logout) -> logout.logoutUrl(this.adminServer.path("/logout"))).httpBasic(Customizer.withDefaults()) 
	        .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) 
	            .ignoringRequestMatchers(
	                new AntPathRequestMatcher(this.adminServer.path("/instances"),
	                    HttpMethod.POST.toString()), 
	                new AntPathRequestMatcher(this.adminServer.path("/instances/*"),
	                    HttpMethod.DELETE.toString()), 
	                new AntPathRequestMatcher(this.adminServer.path("/actuator/**")) 
	            ));
	       // .rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
	  }

	  //Para autenticación en memoria, las credenciales son user y password
	 /* @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
	  }*/
	}
