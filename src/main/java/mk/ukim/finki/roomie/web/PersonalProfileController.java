package mk.ukim.finki.roomie.web;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.PersonalProfile;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.UserService;
import mk.ukim.finki.roomie.service.implementation.PersonalProfileServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "public/api/PersonalProfile")
public class PersonalProfileController {
	
	@Autowired
	private PersonalProfileServiceImpl personalProfileService;
	
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
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody User store(@RequestBody PersonalProfile personalProfile) {
		// TODO: Implement the method: @RequestBody for User in the method parameters
		personalProfile.setFor_user(userService.getUserById(personalProfile.getUser_id()));
		System.out.println("personal profile post "+ personalProfile);
		return personalProfileService.storePersonalProfile(personalProfile);	
	}
	
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody User update(@PathVariable int id,@RequestBody PersonalProfile personalProfile) {
		personalProfile.setFor_user(userService.getUserById(personalProfile.getUser_id()));
		System.out.println("personal profile post "+ personalProfile);
		return personalProfileService.updatePersonalProfile(personalProfile);	
	}
	
	
	/**
	 * Group the users by category (any property of the profile)
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/groupby/{category}", method = RequestMethod.GET)
	public @ResponseBody List<CategoryFrequency> groupby(@PathVariable String category) throws Exception {
		  return personalProfileService.usersGroupBy(category);	  
	}
	
	/**
	 * 
	 * @return percent of female users
	 */
	@RequestMapping(value = "/female", method = RequestMethod.GET)
	public @ResponseBody Double female(){
		  List<CategoryFrequency> genderFrequencies=personalProfileService.usersGroupBy("gender");
		  Long numFemale=0L;
		  Long numMale=0L;
		  for (CategoryFrequency categoryFrequency : genderFrequencies) {
			  
			if(categoryFrequency.getCategory().equals("Female")){
				numFemale = categoryFrequency.getNum();
			}else{
				numMale = categoryFrequency.getNum();
			}
		  }
		  double percent =  numFemale*100.0/(numMale+numFemale);
		  return Math.round(percent * 100.0) / 100.0;
		  
	}

}
