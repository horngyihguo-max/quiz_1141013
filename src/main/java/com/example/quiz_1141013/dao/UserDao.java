package com.example.quiz_1141013.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz_1141013.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	@Modifying
	@Transactional
	@Query(value = "insert into user(email, account, password)" //
			+ " values (?1, ?2, ?3)", nativeQuery = true)
	public void addUser(String email, String account, String password);
}
