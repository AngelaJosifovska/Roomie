package mk.ukim.finki.roomie.service.impl;

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

	public Rating getRatingById(Long id) {
		// TODO Auto-generated method stub
		return ratingRepository.getRatingById(id);
	}

	public List<Rating> getAllRatings(Long rental_id) {
		// TODO Auto-generated method stub
		return ratingRepository.findAll(rental_id);
	}

	public Rating storeRating(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.saveOrUpdate(rating);
	}

	public Rating updateRating(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.saveOrUpdate(rating);
	}

}
