package com.example.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.medico.security.AuthenticationSuccess;
import com.example.medico.serviceImpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
		@Autowired
	    private UserDetailsServiceImpl userDetailsService;
		/*@Autowired
		private AuthenticationFailure authenticationFailure;*/
		@Autowired
		private  AuthenticationSuccess authenticationSuccess;
		
		@Bean
		public BCryptPasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}		
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                    .antMatchers("/resources/**", "/registration","/","/login").permitAll()
	                    .anyRequest().authenticated();
	            
	        http.authorizeRequests().antMatchers("/type/**","/company/**","/unit/**","/medicine/**").hasAnyRole("ADMIN","OPERATOR").anyRequest().authenticated();
	        http.authorizeRequests().antMatchers("/admin/**","/operator").hasRole("ADMIN").anyRequest().authenticated();
	        http.authorizeRequests().antMatchers("/operator/dashboard").hasRole("OPERATOR").anyRequest().authenticated();
	        http.formLogin()
	                    .loginPage("/login")
	                    .permitAll().loginProcessingUrl("/j_security_check")
	                    			.usernameParameter("j_username")
	                    			.passwordParameter("j_password").successHandler(authenticationSuccess)
	                    			.failureUrl("/login?error=true");
	        http.logout().logoutUrl("/logout")
	                    .permitAll();
	        http.csrf().disable();
	    }

	    
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	    }
}
