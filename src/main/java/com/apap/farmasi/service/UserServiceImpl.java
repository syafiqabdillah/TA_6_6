package com.apap.farmasi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.farmasi.model.UserModel;
import com.apap.farmasi.repository.UserDB;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDB userDb;

	@Override
	public UserModel addUser(UserModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
	
}
