package com.sdi.persistence.impl;

import java.util.Date;
import java.util.List;

import com.sdi.model.Rating;
import com.sdi.persistence.RatingFinder;
import com.sdi.persistence.util.Jpa;

public class RatingFinderImpl implements RatingFinder {

	@Override
	public Rating findByAboutFrom(Long aboutUserId, Long aboutTripId,
			Long fromUserId, Long fromTripId) {
		List<Rating> ratings = Jpa.getManager()
				.createNamedQuery("Rating.findByAboutFrom", Rating.class)
				.setParameter(1, aboutUserId).setParameter(2, aboutTripId)
				.getResultList();
		return (ratings.size() > 0) ? ratings.get(0) : null;
	}

	@Override
	public Rating findByAboutFromAndTripID(Long fromUserId, Long fromTripId) {
		List<Rating> ratings = Jpa.getManager()
				.createNamedQuery("Rating.findByUserAndTrip", Rating.class)
				.setParameter(1, fromUserId).setParameter(2, fromTripId)
				.getResultList();
		return (ratings.size() > 0) ? ratings.get(0) : null;
	}

	@Override
	public List<Rating> findRatingsByUserAboutId(Long id) {
		return Jpa
				.getManager()
				.createNamedQuery("Rating.findRatingsByUserAboutId",
						Rating.class).setParameter(1, id).getResultList();
	}

	@Override
	public List<Rating> findRatingsByUserFromId(Long id) {
		return Jpa
				.getManager()
				.createNamedQuery("Rating.findRatingsByUserFromId",
						Rating.class).setParameter(1, id).getResultList();
	}

	@Override
	public List<Rating> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Rating.findAllRatingsOrdered", Rating.class)
				.getResultList();
	}

	@Override
	public void newRating(Rating rating) {
		Jpa.getManager().persist(rating);
	}

	@Override
	public void updateRating(Rating rating) {
		Jpa.getManager().merge(rating);
	}

	@Override
	public void deleteRating(Rating rating) {
		Jpa.getManager().remove(rating);
	}

	@Override
	public Rating findById(long id) {
		List<Rating> ratings = Jpa.getManager()
				.createNamedQuery("Rating.findByID", Rating.class)
				.setParameter(1, id).getResultList();
		return (ratings.size() > 0) ? ratings.get(0) : null;
	}

	@Override
	public List<Rating> findLatestRatings(Date limitDate) {
		return Jpa.getManager()
				.createNamedQuery("Rating.findLatestRatings", Rating.class)
				.setParameter(1, limitDate).getResultList();
	}

}
