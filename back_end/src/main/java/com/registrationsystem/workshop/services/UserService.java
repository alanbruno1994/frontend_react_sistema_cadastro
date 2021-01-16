package com.registrationsystem.workshop.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registrationsystem.workshop.entities.UserSystem;
import com.registrationsystem.workshop.repository.UserRepository;
import com.registrationsystem.workshop.services.exceptions.DuplicateElement;

@Service
public class UserService  {

	@Autowired
	private UserRepository userRepository;

	public void insertUser(UserSystem user) {
		try {
			userRepository.save(user);
		} catch (ConstraintViolationException e) {
			throw new DuplicateElement("Existe usuario ja existe!");
		} catch (IllegalStateException e) {
			throw new DuplicateElement("Existe usuario ja existe!");
		} catch (Exception e) {
			throw new DuplicateElement("Existe usuario ja existe!");
		}
	}

	public UserSystem findByUserAndPassword(String user,String senha) {
		UserSystem userSystem = userRepository.findByUserAndPassoword(user, senha);
		return userSystem;
	}
	
	public UserSystem findByUser(String user) {
		UserSystem userSystem = userRepository.findByUser(user);
		return userSystem;
	}


}
