package com.example.medico.security;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.medico.dao.UserDao;
import com.example.medico.model.Role;
import com.example.medico.model.User;
@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userDao.findByEmail(email);
		System.err.println(user.getPassword());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Role role:user.getRoles())
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);	
     }

}
