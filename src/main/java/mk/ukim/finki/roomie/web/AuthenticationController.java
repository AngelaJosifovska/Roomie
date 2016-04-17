package mk.ukim.finki.roomie.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.roomie.auth.Credentials;
import mk.ukim.finki.roomie.auth.TokenAuthenticationService;
import mk.ukim.finki.roomie.auth.UserAuthentication;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "public/api/authenticate")
public class AuthenticationController {
	@Autowired
	UserService userService;
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> authenticate(@RequestBody Credentials credentials, HttpServletResponse response){
		 User user;
	        try {
	            user = (User) userService.loadUserByUsername(credentials.getEmail());
	            mk.ukim.finki.roomie.model.User origUser = userService.getUserByUsername(credentials.getEmail());
	            System.out.println(passwordEncoder);
	            //if(credentials.getPassword().equals(user.getPassword())){
	            if(passwordEncoder.matches(credentials.getPassword(), user.getPassword())){
	            	UserAuthentication authentication=new UserAuthentication(user,origUser);
	            	String token=tokenAuthenticationService.addAuthentication(response, authentication);
	            	HashMap<String,String> obj=new HashMap<String,String>();
	            	obj.put("token", token);
	            	return new ResponseEntity<Object>(obj,HttpStatus.OK);
	            }else{
	            	Error error=new Error("invalid_credentials");
	            	return new ResponseEntity<Object>(error,HttpStatus.valueOf(401));
	            }
	        } catch (Exception e) {
	        	Error error=new Error("could_not_create_token");
            	return new ResponseEntity<Object>(error,HttpStatus.valueOf(500));
	        }

	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody HashMap<String,mk.ukim.finki.roomie.model.User> getAuthenticatedUser(HttpServletRequest request){
		UserAuthentication auth = tokenAuthenticationService.getAuthentication(request);
		HashMap<String,mk.ukim.finki.roomie.model.User> obj=new HashMap<String,mk.ukim.finki.roomie.model.User>();
    	obj.put("user", auth.getUser());
		return obj;

	}
}
