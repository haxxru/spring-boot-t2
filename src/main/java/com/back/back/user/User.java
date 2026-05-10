package com.back.back.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String userId;

	@Column(nullable = false, length = 50)
	private String nickname;

	@Column(nullable = false, length = 255)
	private String password;

	public User(String userId, String nickname, String password) {
		this.userId = userId;
		this.nickname = nickname;
		this.password = password;
	}
}
