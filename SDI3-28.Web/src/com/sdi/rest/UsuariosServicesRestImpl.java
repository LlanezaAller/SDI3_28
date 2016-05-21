package com.sdi.rest;

import java.util.List;
import java.util.Set;

import com.sdi.business.UsuariosService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;

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
	public Trip promoterManageApplication(SeatStatus status, String login,
			Long tripId) {

		return promoterManageApplication(status, login, tripId);
	}

	@Override
	public void aplicarUsuarios(Set<User> apps, Trip trip) {
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
	public long getIdByLogin(String login) throws EntityNotFoundException {
		return service.getIdByLogin(login);
	}

}
