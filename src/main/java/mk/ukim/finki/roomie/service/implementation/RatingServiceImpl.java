package mk.ukim.finki.roomie.service.implementation;

import mk.ukim.finki.roomie.model.Rating;
import mk.ukim.finki.roomie.repository.RatingRepository;
import mk.ukim.finki.roomie.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository ratingRepository;

	public Rating getRatingByRentalUnitID(Integer rental_id) {
		return ratingRepository.getRatingByRentalUnitID(rental_id);
	}

	public Double getAllRatingsForRentalUnit(Integer rental_id) {
		return ratingRepository.getAverageRatingForRentalUnit(rental_id);
	}

	public Rating storeRating(Rating rating) {
		return ratingRepository.saveOrUpdate(rating);
	}

	public Rating updateRating(Rating rating) {
		return ratingRepository.saveOrUpdate(rating);
	}

}
