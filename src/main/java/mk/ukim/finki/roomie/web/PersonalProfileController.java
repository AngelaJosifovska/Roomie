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
	public @ResponseBody List<UserTmp> index() {
		UserTmp unit = new UserTmp("All users");
		List<UserTmp> list = new ArrayList<UserTmp>();
		list.add(unit);
		return list;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody UserTmp store() {
		UserTmp unit = new UserTmp("New user");
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
//		User unit = new User("User number: " + id);
//		return unit;'
		return this.personalProfileService.getSinglePersonalProfile(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody UserTmp update(@PathVariable long id) {
		UserTmp unit = new UserTmp("Updated user: " + id);
		return unit;
	}

}
