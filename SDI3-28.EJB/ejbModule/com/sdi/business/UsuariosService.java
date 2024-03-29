package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;

public interface UsuariosService {

	void saveUser(User user) throws EntityAlreadyExistsException;

	User findUser(String user) throws EntityNotFoundException;

	Trip cancelarAplicacion(String login, Long tripId);

	Trip aplicar(String login, Long tripId);

	Trip promoterManageApplication(SeatStatus status, String login, Long tripId);

	void aplicarUsuarios(List<User> apps, Trip trip);

	List<User> findAll();

	void disableUser(String login);
	
	long getIdByLogin(String login) throws EntityNotFoundException;
	
	List<User> findUsersByTrip(long id);

	List<User> findUnconfirmedUsersByTrip(long id);
}
