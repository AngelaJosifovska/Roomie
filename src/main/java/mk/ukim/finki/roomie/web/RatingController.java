package mk.ukim.finki.roomie.web;

import mk.ukim.finki.roomie.model.Rating;
import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;
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
	private RatingService ratingService;
	@Autowired
	private RentalUnitService rentalUnitService;
	@Autowired
	private UserService userService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/User", method = RequestMethod.GET)
	public @ResponseBody Double index(@PathVariable int property_id) {
		return ratingService.getAllRatingsForRentalUnit(property_id);
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/User", method = RequestMethod.POST)
	public @ResponseBody Rating store(@PathVariable int property_id, @RequestBody Rating rating, @RequestParam int user_id) {
		
		RentalUnit property = rentalUnitService.getRentalUnitById(property_id);		
		rating.setRentalUnit(property);
		
		User user = userService.getUserById(user_id);
		rating.setUser(user);
		
		return ratingService.storeRating(rating);	
		
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/User/{id}", method = RequestMethod.GET)
	public @ResponseBody Rating show(@PathVariable int property_id, @PathVariable int id) {
		return ratingService.getRatingByRentalUnitID(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/User/{id}", method = RequestMethod.PUT)
	public @ResponseBody Rating update(@PathVariable int property_id, @PathVariable int id, @RequestBody Rating rating) {
	    Rating old = ratingService.getRatingByRentalUnitID(id);
	    old.setRating_points(rating.getRating_points());
		return ratingService.updateRating(old);
	}
	
}
