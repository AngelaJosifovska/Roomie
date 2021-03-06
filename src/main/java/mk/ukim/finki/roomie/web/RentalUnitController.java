package mk.ukim.finki.roomie.web;

import java.util.List;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.helper.HelperPaginatedResponse;
import mk.ukim.finki.roomie.model.Location;
import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.GoogleGeocoderService;
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

import com.google.maps.model.GeocodingResult;


@RestController
@RequestMapping(value = "public/api/RentalUnit")
public class RentalUnitController {
	
	@Autowired
	private RentalUnitService rentalUnitService;
	@Autowired
	private UserService userService;
	@Autowired
	private GoogleGeocoderService geocoderService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody HelperPaginatedResponse<RentalUnit> index(@RequestParam int page) {
		long total = rentalUnitService.getTotal();
		int maxResults=10;
		List<RentalUnit> data = rentalUnitService.getAllRentalUnits(page,maxResults);
		HelperPaginatedResponse<RentalUnit> response=new HelperPaginatedResponse<RentalUnit>(total, maxResults, page, data);
		return response;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody RentalUnit store(@RequestBody RentalUnit rentalUnit) {
		User user=userService.getUserById(rentalUnit.getUser_id());
		rentalUnit.setUser(user);
		GeocodingResult result = geocoderService.getLocationFromAddress(rentalUnit.getAddress(), rentalUnit.getCity());
		Location location=new Location(result.geometry.location.lat, result.geometry.location.lng);
		rentalUnit.setLocation(location);
		
		return rentalUnitService.storeRentalUnit(rentalUnit);
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody RentalUnit show(@PathVariable int id) {
		return rentalUnitService.getRentalUnitById(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody RentalUnit update(@PathVariable int id,@RequestBody RentalUnit rentalUnit) {
		User user=userService.getUserById(rentalUnit.getUser_id());
		rentalUnit.setUser(user);
		GeocodingResult result = geocoderService.getLocationFromAddress(rentalUnit.getAddress(), rentalUnit.getCity());
		Location location=new Location(result.geometry.location.lat, result.geometry.location.lng);
		rentalUnit.setLocation(location);
		RentalUnit ru =  rentalUnitService.updateRentalUnit(rentalUnit);
		System.out.println(ru.toString());
		return ru;
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public @ResponseBody GeocodingResult example() throws Exception {
		return geocoderService.getLocationFromAddress("1600 Amphitheatre Parkway", "Mountain View");
	}
	/**
	 * Group by category (any property of the rental unit)
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/groupby/{category}", method = RequestMethod.GET)
	public @ResponseBody List<CategoryFrequency> groupby(@PathVariable String category) throws Exception {
		  return rentalUnitService.rentalUnitsGroupBy(category);	  
	}
	
	/**
	 * 
	 * @return total number of rental unirs
	 */
	@RequestMapping(value = "/total", method = RequestMethod.GET)
	public @ResponseBody Long total() {
		  return rentalUnitService.getTotal();  
	}

}
