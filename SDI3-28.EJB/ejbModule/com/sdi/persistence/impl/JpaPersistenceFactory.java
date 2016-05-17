package com.sdi.persistence.impl;

import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.RatingFinder;
import com.sdi.persistence.SeatFinder;
import com.sdi.persistence.SystemFinder;
import com.sdi.persistence.TripFinder;
import com.sdi.persistence.UserFinder;

public class JpaPersistenceFactory implements PersistenceFactory {
	@Override
	public TripFinder CreateTripFinder() {
		return new TripFinderImpl();
	}

	@Override
	public SeatFinder CreateSeatFinder() {
		return new SeatFinderImpl();
	}

	@Override
	public RatingFinder CreateRatingFinder() {
		return new RatingFinderImpl();
	}

	@Override
	public UserFinder CreateUserFinder() {
		return new UserFinderImpl();
	}

	@Override
	public SystemFinder CreateSystemFinder() {
		return new SystemFinderImpl();
	}
}
