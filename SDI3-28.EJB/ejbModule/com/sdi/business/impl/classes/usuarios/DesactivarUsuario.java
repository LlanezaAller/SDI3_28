package com.sdi.business.impl.classes.usuarios;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;
import com.sdi.model.type.UserStatus;
import com.sdi.persistence.SeatFinder;

public class DesactivarUsuario implements Command {

	private String login;

	public DesactivarUsuario(String login) {
		this.login=login;
	}

	@Override
	public Object execute() {
		User user = Factories.persistence.CreateUserFinder().findByLogin(login);
		SeatFinder seatService = Factories.persistence.CreateSeatFinder();
		user.setStatus(UserStatus.CANCELLED);
		for(Seat s:seatService.findByUser(user.getId()))
			if(s.getSeatTrip().getStatus()==TripStatus.OPEN){
				s.setStatus(SeatStatus.EXCLUDED);
			}
		return null;		
	
	}

}
