package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.model.Rating;

public interface RatingService {

	public Rating getRatingByRentalUnitID(Integer id);
	
	public List<Rating> getAllRatingsForRentalUnit(Integer rental_id);
	
	public Rating storeRating(Rating Rating);
	
	public Rating updateRating(Rating Rating);
}
