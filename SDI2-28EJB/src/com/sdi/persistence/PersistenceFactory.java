package com.sdi.persistence;

public interface PersistenceFactory {

	UserFinder CreateUserFinder();

	TripFinder CreateTripFinder();

	SeatFinder CreateSeatFinder();

	RatingFinder CreateRatingFinder();

	SystemFinder CreateSystemFinder();
}
