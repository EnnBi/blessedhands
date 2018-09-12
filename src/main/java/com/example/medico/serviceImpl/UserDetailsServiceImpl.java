package com.example.medico.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.medico.dao.UserDao;
import com.example.medico.model.Role;
import com.example.medico.model.User;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userRepository;

	@Autowired

	BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + email);
		}

		
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));

		
	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(roles));
		return authList;
	}

	private List<String> getRoles(List<Role> roles) {
		List<String> roless = new ArrayList<String>();
		for (Role r : roles) {

			roless.add(r.getName());

		}
		return roless;
	}

	@SuppressWarnings("unused")
	private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String role:roles)
			authorities.add(new SimpleGrantedAuthority(role));
		
		return authorities;
	}
}
