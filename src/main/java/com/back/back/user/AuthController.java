package com.back.back.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;

	@GetMapping("/join")
	public String joinForm() {
		return "auth/join";
	}

	@PostMapping("/join")
	public String join(
			@RequestParam String userId,
			@RequestParam String nickname,
			@RequestParam String password,
			@RequestParam String passwordConfirm,
			Model model
	) {
		String errorMessage = userService.join(userId, nickname, password, passwordConfirm);

		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("userId", userId);
			model.addAttribute("nickname", nickname);
			return "auth/join";
		}

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "auth/login";
	}
}
