package mk.ukim.finki.roomie.web;

import java.util.List;

import mk.ukim.finki.roomie.model.User;
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
@RequestMapping(value = "public/api/User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody HelperPaginatedResponse<User> index(@RequestParam int page) {
		long total = userService.getTotal();
		int maxResults=10;
		List<User> data = userService.getAllUsers(page,maxResults);
		HelperPaginatedResponse<User> response=new HelperPaginatedResponse<User>(total, maxResults, page, data);
		return response;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody User store(@RequestBody User user) {
		System.out.println(user.getName() + " " + user.getPassword());
		return userService.storeUser(user);
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User show(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody User update(@PathVariable int id, @RequestBody User user) {
		return userService.updateUser(user);
	}

}
