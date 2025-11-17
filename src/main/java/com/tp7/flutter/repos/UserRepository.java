package com.tp7.flutter.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp7.flutter.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
