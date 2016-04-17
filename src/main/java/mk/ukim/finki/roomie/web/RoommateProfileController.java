package mk.ukim.finki.roomie.web;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.roomie.model.RoommateProfile;
import mk.ukim.finki.roomie.service.RoommateProfileService;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import mk.ukim.finki.roomie.model.User;





import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "public/api/RoommateProfile")
public class RoommateProfileController {
	
	@Autowired
	RoommateProfileService roommateProfileService;
	
	@Autowired
	UserService userService;
	
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
	public @ResponseBody User store(@RequestBody RoommateProfile roommateProfile) {
		roommateProfile.setFor_user(userService.getUserById(roommateProfile.getUser_id()));
		System.out.println("personal profile post "+ roommateProfile);
		return roommateProfileService.storeRoomateProfile(roommateProfile);	
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody RoommateProfile show(@PathVariable int id) {
		return this.roommateProfileService.getSingleRoommateProfile(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody User update(@PathVariable int id, @RequestBody RoommateProfile roommateProfile) {

		roommateProfile.setFor_user(userService.getUserById(roommateProfile.getUser_id()));
		System.out.println("personal profile post "+ roommateProfile);
		return roommateProfileService.storeRoomateProfile(roommateProfile);	
	}
}
