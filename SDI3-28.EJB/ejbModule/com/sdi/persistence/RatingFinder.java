package com.sdi.persistence;

import java.util.Date;
import java.util.List;

import com.sdi.model.Rating;

public interface RatingFinder {

	Rating findByAboutFrom(Long aboutUserId, Long aboutTripId, Long fromUserId,
			Long fromTripId);

	List<Rating> findRatingsByUserAboutId(Long id);

	void newRating(Rating rating);

	void updateRating(Rating rating);

	List<Rating> findRatingsByUserFromId(Long id);

	Rating findByAboutFromAndTripID(Long fromUserId, Long fromTripId);

	void deleteRating(Rating rating);

	List<Rating> findAll();

	Rating findById(long id);

	List<Rating> findLatestRatings(Date limitDate);

}
