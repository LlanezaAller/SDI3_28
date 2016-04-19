package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Seat;

public interface SeatFinder {

	Seat findByUserAndTrip(Long userId, Long tripId);

	void newSeat(Seat seat);

	List<Seat> findByUser(Long userId);

	void removeSeat(Seat s);

}
