package com.diva.thymeleaf_demo.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diva.thymeleaf_demo.model.User;
import com.diva.thymeleaf_demo.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

    @GetMapping("/")
	public String home(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());
		model.addAttribute("message", "Hello, Thymeleaf!");
		return "home";
	}

	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("departments", Arrays.asList("HR", "IT", "Finance"));
		model.addAttribute("roles", Arrays.asList("Admin", "User", "Manager"));
		return "addUser";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.get(id));
		model.addAttribute("departments", Arrays.asList("HR", "IT", "Finance"));
		model.addAttribute("roles", Arrays.asList("Admin", "User", "Manager"));
		return "addUser";
	}


	@PostMapping("/users")
	public String saveUser(@ModelAttribute User user, Model model) {
		userService.save(user);
		model.addAttribute("user-save", "User registration successful");
		return "redirect:/";
	}

	@PutMapping("/users")
	public String updateUser(@ModelAttribute User user) {
		userService.update(user);
		return "redirect:/users";
	}


	@DeleteMapping("/api/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}


	@GetMapping("/api/users")
	@ResponseBody
	public List<User> listUsers() {
		return userService.getAll();
	}
}
