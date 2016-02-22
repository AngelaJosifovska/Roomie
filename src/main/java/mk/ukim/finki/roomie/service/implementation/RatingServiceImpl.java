package mk.ukim.finki.roomie.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.Rating;
import mk.ukim.finki.roomie.repository.RatingRepository;
import mk.ukim.finki.roomie.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	RatingRepository ratingRepository;

	public Rating getRatingByRentalUnitID(Integer id) {
		return ratingRepository.getRatingById(id);
	}

	public List<Rating> getAllRatingsForRentalUnit(Integer rental_id) {
		return ratingRepository.findAll(rental_id);
	}

	public Rating storeRating(Rating rating) {
		return ratingRepository.saveOrUpdate(rating);
	}

	public Rating updateRating(Rating rating) {
		return ratingRepository.saveOrUpdate(rating);
	}

}
