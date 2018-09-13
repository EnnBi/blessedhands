package com.example.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.medico.security.JwtAuthenticationFilter;
import com.example.medico.security.RestAuthentication;
import com.example.medico.serviceImpl.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@Order(1)
public class RestAuthenticationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	/*@Autowired
	private AuthenticationFailure authenticationFailure;
	@Autowired
	private  AuthenticationSuccess authenticationSuccess;*/
	
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}		
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	  http
          .antMatcher("/api/**")                               
          .authorizeRequests();
        http.authorizeRequests().antMatchers("/api/login","/api/register","api/images/**").permitAll()
             .anyRequest().authenticated();
            
        http.authorizeRequests().antMatchers("/api/**").hasAnyRole().and().httpBasic().authenticationEntryPoint(restAuthentication());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterAfter(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
    }
    
    @Bean
    public RestAuthentication restAuthentication() {
    	return new RestAuthentication();
    }
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    	JwtAuthenticationFilter jwtAuthenticationFilter =  new JwtAuthenticationFilter();
    	jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
    	return jwtAuthenticationFilter;
	}
    
    @Override
    protected void configure(
      AuthenticationManagerBuilder auth) throws Exception {
  
        auth.userDetailsService(userDetailsService);
    }
}
