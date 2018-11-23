package com.apap.farmasi.service;

import com.apap.farmasi.model.UserModel;

public interface UserService {
	UserModel addUser(UserModel user);
	public String encrypt(String password);
}
