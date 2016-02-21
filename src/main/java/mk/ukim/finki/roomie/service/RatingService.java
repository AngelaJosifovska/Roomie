package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.model.Rating;

public interface RatingService {

	public Rating getRatingById(Long id);
	
	public List<Rating> getAllRatings(Long rental_id);
	
	public Rating storeRating(Rating Rating);
	
	public Rating updateRating(Rating Rating);
}
