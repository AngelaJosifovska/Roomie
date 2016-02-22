package mk.ukim.finki.roomie.web;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.implementation.PersonalProfileServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "public/api/PersonalProfile")
public class PersonalProfileController {
	
	@Autowired
	private PersonalProfileServiceImplementation personalProfileService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody List<User> index() {
		// TODO: Implement the method if necessary
		User unit = new User();
		List<User> list = new ArrayList<User>();
		list.add(unit);
		return list;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody User store() {
		// TODO: Implement the method: @RequestBody for User in the method parameters
		User unit = new User();
		return unit;
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User show(@PathVariable int id) {
		return this.personalProfileService.getSinglePersonalProfile(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody User update(@PathVariable int id) {
		// TODO: Implement the method: @RequestBody for User in the method parameters
		return new User();
	}

}
