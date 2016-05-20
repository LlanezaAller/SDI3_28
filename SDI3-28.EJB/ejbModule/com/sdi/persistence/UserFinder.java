package com.sdi.persistence;

import java.util.List;

import com.sdi.model.User;

public interface UserFinder {

	User findByLogin(String login);

	List<User> findAll();

	void update(User usuario);

	void newUser(User usuario);

	List<User> findUsersByTrip(Long id);

	Long getIdByLogin(String login);

}
