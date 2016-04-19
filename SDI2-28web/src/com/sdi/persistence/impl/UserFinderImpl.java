package com.sdi.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.sdi.model.User;
import com.sdi.persistence.UserFinder;
import com.sdi.persistence.util.Jpa;

public class UserFinderImpl implements UserFinder {

	@Override
	public List<User> findAll() {
		return Jpa.getManager().createNamedQuery("User.findAll", User.class)
				.getResultList();
	}

	@Override
	public User findByLogin(String login) {
		List<User> usuarios = Jpa.getManager()
				.createNamedQuery("User.findUserByLogin", User.class)
				.setParameter(1, login).getResultList();
		return (usuarios.size() > 0) ? usuarios.get(0) : null;
	}

	@Override
	public List<User> findUsersByTrip(Long id) {
		List<User> usuarios = Jpa.getManager()
				.createNamedQuery("User.findAllByTrip", User.class)
				.setParameter(1, id).getResultList();
		return (usuarios != null) ? usuarios : new ArrayList<User>();
	}

	@Override
	public void update(User usuario) {
		Jpa.getManager().merge(usuario);
	}

	@Override
	public void newUser(User usuario) {
		Jpa.getManager().persist(usuario);
	}
}
