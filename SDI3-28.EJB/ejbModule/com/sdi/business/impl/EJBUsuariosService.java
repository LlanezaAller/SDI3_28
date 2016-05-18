package com.sdi.business.impl;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.usuarios.AllUsuariosFinder;
import com.sdi.business.impl.classes.usuarios.Aplicar;
import com.sdi.business.impl.classes.usuarios.AplicarUsuarios;
import com.sdi.business.impl.classes.usuarios.CancelarAplicacion;
import com.sdi.business.impl.classes.usuarios.PromoterManageApplication;
import com.sdi.business.impl.classes.usuarios.UsuariosAlta;
import com.sdi.business.impl.classes.usuarios.UsuariosFinder;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;

@Stateless
public class EJBUsuariosService implements RemoteUsuariosService, LocalUsuariosService {

	@Override
	public void saveUser(User user) throws EntityAlreadyExistsException {
		new UsuariosAlta(user).execute();
	}

	@Override
	public User findUser(String user) throws EntityNotFoundException {
		return (User) new UsuariosFinder(user).execute();
	}
	@Override
	public List<User> findAll(){
		return (List<User>) new AllUsuariosFinder().execute();
	}
	
	

	@Override
	public Trip cancelarAplicacion(String login, Long tripId) {
		return (Trip)new CancelarAplicacion(
				login, tripId).execute();
	}

	@Override
	public Trip aplicar(String login, Long tripId) {
		return (Trip) new Aplicar(login, tripId).execute();
	}

	@Override
	public Trip promoterManageApplication(SeatStatus status, String login,
			Long tripId) {
		return (Trip) new PromoterManageApplication(status, login, tripId).execute();
	}

	@Override
	public void aplicar(Set<User> apps, Trip trip) {
		new AplicarUsuarios(apps, trip).execute();
	}

}
