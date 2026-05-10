package com.back.back.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		com.back.back.user.User user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

		return User.builder()
				.username(user.getUserId())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}
}
