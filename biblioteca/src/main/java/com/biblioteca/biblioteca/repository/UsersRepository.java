package com.biblioteca.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.model.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

	Optional<UsersModel> findByLoginAndPassword(String login, String passwordString);
}
