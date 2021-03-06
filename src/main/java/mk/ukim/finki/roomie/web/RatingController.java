package mk.ukim.finki.roomie.web;

import mk.ukim.finki.roomie.model.Rating;
import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.RatingService;
import mk.ukim.finki.roomie.service.RentalUnitService;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
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
	@RequestMapping(value = "/{property_id}/User/{user_id}", method = RequestMethod.POST)
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
	@RequestMapping(value = "/{property_id}/User/{user_id}", method = RequestMethod.GET)
	public @ResponseBody Rating show(@PathVariable int property_id, @PathVariable int user_id) {
		return ratingService.getRatingByRentalUnitID(property_id, user_id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/User/{user_id}", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Rating update(@PathVariable int property_id, @PathVariable int user_id, @RequestBody MultiValueMap<String,String> body) {
		
		System.out.println(body.getFirst("from_user"));
		System.out.println(body.getFirst("on_rental"));
		System.out.println(body.getFirst("rating_points"));
		
	    Rating old = ratingService.getRatingByRentalUnitID(property_id, user_id);
	    if(old == null) {
	    	User user = userService.getUserById(Integer.valueOf(body.getFirst("from_user")));
	    	RentalUnit property = rentalUnitService.getRentalUnitById(Integer.valueOf(body.getFirst("on_rental")));
	    	Integer rating = Integer.valueOf(body.getFirst("rating_points"));
	    	old = new Rating(rating, user, property);
	    } else {
	    	old.setRating_points(Integer.valueOf(body.getFirst("rating_points")));
	    }
	    
		return ratingService.updateRating(old);
	}
	
}
