package mk.ukim.finki.roomie.service;

import mk.ukim.finki.roomie.model.Rating;

public interface RatingService {

	public Rating getRatingByRentalUnitID(Integer rental_id, Integer user_id);
	
	public Double getAllRatingsForRentalUnit(Integer rental_id);
	
	public Rating storeRating(Rating Rating);
	
	public Rating updateRating(Rating Rating);
}
