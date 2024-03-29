package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.TripUser;
import com.sdi.model.User;
import com.sdi.model.type.SeatStatus;
import com.sdi.model.type.TripStatus;

public interface ViajesService {

	Trip findTripByID(Long id) throws EntityNotFoundException;
	
	Seat[] findSeatsFromTrip(Long id) throws EntityNotFoundException;

	void saveTrip(Trip viaje) throws EntityAlreadyExistsException;

	List<Trip> findAllTrips();

	List<Trip> findAllUserApplications(Long id);

	List<Trip> findAllTripsByStatus(TripStatus status);

	List<Trip> findAllTripsByPromoterId(Long id);
	
	List<Trip> findTripsByUserAndStatus(Long userID, TripStatus tripStatus, SeatStatus seatStatus);

	void update(Trip t);

	int cancelTrips(List<TripUser> selectedTripsRelation, User user);
	
	boolean hasUserApplication(User user, Trip trip);
	
	boolean hasUserSeat(User user, Trip trip) throws EntityNotFoundException;
	
}
