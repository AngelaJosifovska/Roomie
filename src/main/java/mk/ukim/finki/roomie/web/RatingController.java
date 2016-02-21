package mk.ukim.finki.roomie.web;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.roomie.model.Comment;
import mk.ukim.finki.roomie.model.Rating;
import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.CommentService;
import mk.ukim.finki.roomie.service.RatingService;
import mk.ukim.finki.roomie.service.RentalUnitService;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "public/api/RentalUnit")
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	@Autowired
	RentalUnitService rentalUnitService;
	@Autowired
	UserService userService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Rating", method = RequestMethod.GET)
	public @ResponseBody List<Rating> index(@PathVariable long property_id) {
		return ratingService.getAllRatings(property_id);
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Rating", method = RequestMethod.POST)
	public @ResponseBody Rating store(@PathVariable long property_id, @RequestBody Rating rating,@RequestParam Long user_id) {
		
		RentalUnit property=rentalUnitService.getRentalUnitById(property_id);		
		rating.setRentalUnit(property);
		
		User user=userService.getUserById(user_id);
		rating.setUser(user);
		
		return ratingService.storeRating(rating);	
		
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Rating/{id}", method = RequestMethod.GET)
	public @ResponseBody Rating show(@PathVariable long property_id, @PathVariable long id) {
		return ratingService.getRatingById(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Rating/{id}", method = RequestMethod.PUT)
	public @ResponseBody Rating update(@PathVariable long property_id, @PathVariable long id, @RequestBody Rating rating) {
	    Rating old=ratingService.getRatingById(id);
	    old.setRating_points(rating.getRating_points());
	    old.setBody(rating.getBody());
		return ratingService.updateRating(old);
	}
	
}
