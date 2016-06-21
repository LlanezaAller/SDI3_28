package com.sdi.persistence;

import java.util.Date;
import java.util.List;

import com.sdi.model.Trip;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;

public interface TripFinder {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);

	Trip findById(Long id);

	List<Trip> findAll();

	List<Trip> findAllTripsByPromoterId(Long id);

	void newTrip(Trip trip);

	void updateTrip(Trip trip);

	List<Trip> findAllStatus(TripStatus status);

	List<Trip> findAllAplicantsByUserId(Long id);

	List<Trip> findByUserAndStatus(long userID, TripStatus tripStatus,
			SeatStatus seatStatus);

}
