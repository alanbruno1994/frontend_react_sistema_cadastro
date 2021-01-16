package com.registrationsystem.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registrationsystem.workshop.entities.UserSystem;
import com.registrationsystem.workshop.services.exception.UsernameNotFoundException;

@Service
public class UserServiceAuthenticate{

	@Autowired
    private UserService userService;
	
	
	public UserSystem loadUserByUserNamePassword(String username,String senha) throws UsernameNotFoundException {
		UserSystem user = userService.findByUserAndPassword(username,senha);
		if(user!=null)
		{
			return user;
		}		
		throw new UsernameNotFoundException("User not found");
	}

}
