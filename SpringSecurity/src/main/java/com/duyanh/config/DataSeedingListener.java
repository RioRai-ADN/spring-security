package com.duyanh.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.duyanh.domain.Role;
import com.duyanh.domain.User;
import com.duyanh.repository.RoleRepository;
import com.duyanh.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		
		if(roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		
		
		if(roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		
		
		if(userRepository.findByEmail("riorai@gmail.com") == null) {
			User admin = new User();
			admin.setEmail("riorai@gmail.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}
		
		if (userRepository.findByEmail("member@gmail.com") == null) {
            User user = new User();
            user.setEmail("member@gmail.com");
            user.setPassword(passwordEncoder.encode("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
	}

}
