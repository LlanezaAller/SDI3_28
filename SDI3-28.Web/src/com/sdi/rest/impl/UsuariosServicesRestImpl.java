package com.sdi.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.sdi.business.UsuariosService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.SimpleResponse;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;
import com.sdi.rest.UsuariosServicesRest;

public class UsuariosServicesRestImpl implements UsuariosServicesRest {
	UsuariosService service = Factories.business.getUsuariosService();
	@Override
	public void saveUser(User user) throws EntityAlreadyExistsException {
		service.saveUser(user);
	}

	@Override
	public User findUser(String user) throws EntityNotFoundException {
		return service.findUser(user);
	}

	@Override
	public Trip cancelarAplicacion(String login, Long tripId) {
		return service.cancelarAplicacion(login, tripId);
	}

	@Override
	public Trip aplicar(String login, Long tripId) {
		return service.aplicar(login, tripId);
	}

	@Override
	public Trip confirmUser(String login, Long tripID) {
		System.out.println("Confirmando usuario: " + login + tripID);
		return service.promoterManageApplication(SeatStatus.ACCEPTED, login, tripID);
	}

	@Override
	public void aplicarUsuarios(List<User> apps, Trip trip) {
		service.aplicarUsuarios(apps, trip);
	}

	@Override
	public List<User> findAll() {
		return service.findAll();
	}

	@Override
	public void disableUser(String login) {
		service.disableUser(login);

	}

	@Override
	public SimpleResponse<Long> getIdByLogin(@Context HttpServletRequest request) throws EntityNotFoundException {
		return new SimpleResponse<Long>(((User) request.getAttribute("user")).getId());
	}
	
	@Override
	public List<User> findUnconfirmedUsersByTrip(long id) {
		return service.findUnconfirmedUsersByTrip(id);
	}

}
