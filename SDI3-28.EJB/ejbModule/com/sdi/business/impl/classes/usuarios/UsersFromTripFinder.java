package com.sdi.business.impl.classes.usuarios;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;

public class UsersFromTripFinder implements  Command {

	private long tripId;
	
	public UsersFromTripFinder(long id){
		this.tripId = id;
	}
	
	@Override
	public Object execute() {
		return Factories.persistence.CreateUserFinder().findUsersByTrip(tripId);
	}

	

}
