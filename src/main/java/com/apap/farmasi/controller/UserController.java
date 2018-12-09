package com.apap.farmasi.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.farmasi.model.UserModel;
import com.apap.farmasi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserModel user, Model model, BindingResult result) {

		if(this.validatePassword(user.getPassword())) {
			userService.addUser(user);
		}
		
		return "redirect:/";
	}
	
	public boolean validatePassword(String password) {
		if (password.length() >= 8
				&& Pattern.compile("[a-zA-Z]").matcher(password).find()
				&& Pattern.compile("[0-9]").matcher(password).find()
				) {
			return true;
		}
		return false;
	}
	
}