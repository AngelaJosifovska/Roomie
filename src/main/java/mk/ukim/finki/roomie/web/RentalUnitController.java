package mk.ukim.finki.roomie.web;

import java.util.List;

import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;
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
public class RentalUnitController {
	
	@Autowired
	private RentalUnitService rentalUnitService;
	@Autowired
	private UserService userService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody HelperResponseWrapper<RentalUnit> index() {
		List<RentalUnit> data = rentalUnitService.getAllRentalUnits();
		HelperResponseWrapper<RentalUnit> response = new HelperResponseWrapper<RentalUnit>(data);
		return response;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody RentalUnit store(@RequestParam int user_id, @RequestBody RentalUnit rentalUnit) {
		User user=userService.getUserById(user_id);
		rentalUnit.setUser(user);
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
	public @ResponseBody RentalUnit update(@PathVariable int id, @RequestParam int user_id, @RequestBody RentalUnit rentalUnit) {
		User user=userService.getUserById(user_id);
		rentalUnit.setUser(user);
		return rentalUnitService.updateRentalUnit(rentalUnit);
	}

}
