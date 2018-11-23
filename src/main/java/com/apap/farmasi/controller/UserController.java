package com.apap.farmasi.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.farmasi.model.UserModel;
import com.apap.farmasi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
		String[] msg = {"0","1"};
		
		if(this.validatePassword(user.getPassword())) {
			userService.addUser(user);
			msg[0] = "SUCCESS";
			msg[1] = "User berhasil dibuat !";
		}
		
		else {
			msg[0] = "FAIL";
			msg[1] = "Password harus terdiri dari 8 karakter dan bersifat alfanumerik";
		}
		
		model.addAttribute("message", msg);
		return "home";
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