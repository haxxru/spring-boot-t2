package com.back.back.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public String join(String userId, String nickname, String password, String passwordConfirm) {
		if (userId.isBlank() || nickname.isBlank() || password.isBlank() || passwordConfirm.isBlank()) {
			return "모든 항목을 입력해주세요.";
		}

		if (!password.equals(passwordConfirm)) {
			return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
		}

		if (userRepository.findByUserId(userId).isPresent()) {
			return "이미 사용 중인 유저ID입니다.";
		}

		userRepository.save(new User(userId, nickname, passwordEncoder.encode(password)));
		return null;
	}
}
