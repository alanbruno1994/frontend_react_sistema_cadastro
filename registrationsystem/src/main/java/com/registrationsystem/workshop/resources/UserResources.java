package com.registrationsystem.workshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.registrationsystem.workshop.config.util.JwtTokenUtil;
import com.registrationsystem.workshop.entities.UserSystem;
import com.registrationsystem.workshop.entities.util.JwtResponse;
import com.registrationsystem.workshop.services.util.UserServiceAuthenticate;

@RestController
@RequestMapping(value = "/users")
public class UserResources {



	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceAuthenticate userAuthenticate;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserSystem authenticationRequest) throws Exception {
		try {
		userAuthenticate.loadUserByUserNamePassword(authenticationRequest.getName_user(), authenticationRequest.getPassword());		
		final String token = jwtTokenUtil.doGenerateToken(authenticationRequest);
		System.out.println(">>>>>>6 "+token);
		return ResponseEntity.ok(new JwtResponse(token));
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.noContent().build();
	}


	
}




