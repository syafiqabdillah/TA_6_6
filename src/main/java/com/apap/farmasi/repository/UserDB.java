package com.apap.farmasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.farmasi.model.UserModel;

@Repository
public interface UserDB extends JpaRepository<UserModel, Long>{
	UserModel findByUsername(String username);
}
