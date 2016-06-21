package com.sdi.business.impl.classes.viajes;

import com.sdi.business.Command;
import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;
import com.sdi.persistence.TripFinder;

public class FindTripsByUserAndStatus implements Command {
	private long userID;
	private TripStatus tripStatus;
	private SeatStatus seatStatus;

	public FindTripsByUserAndStatus(Long userID, TripStatus tripStatus,
			SeatStatus seatStatus) {
		this.userID=userID;
		this.tripStatus=tripStatus;
		this.seatStatus=seatStatus;
	}

	@Override
	public Object execute() {
		TripFinder finder = Factories.persistence.CreateTripFinder();
		return finder.findByUserAndStatus(userID, tripStatus, seatStatus);
	}

}
