package mk.ukim.finki.roomie.web;

import java.util.List;

import mk.ukim.finki.roomie.helper.HelperPaginatedResponse;
import mk.ukim.finki.roomie.model.Comment;
import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.CommentService;
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
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private RentalUnitService rentalUnitService;
	@Autowired
	private UserService userService;
	@Autowired
	private RatingService ratingService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment", method = RequestMethod.GET)
	public @ResponseBody HelperPaginatedResponse<Comment> index(@PathVariable int property_id, @RequestParam int page) {
		long total = commentService.getTotal(property_id);
		int maxResults=10;
		List<Comment> data = commentService.getAllCommentsForRentalUnit(property_id, page, maxResults);
		HelperPaginatedResponse<Comment> response=new HelperPaginatedResponse<Comment>(total, maxResults, page, data);
		return response;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment", method = RequestMethod.POST)
	public @ResponseBody Comment store(@PathVariable int property_id, @RequestBody Comment comment, @RequestParam int user_id) {
		
		RentalUnit property=rentalUnitService.getRentalUnitById(property_id);		
		comment.setRentalUnit(property);
		
		User user=userService.getUserById(user_id);
		comment.setUser(user);
		
		return commentService.storeComment(comment);		
		
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment/{id}", method = RequestMethod.GET)
	public @ResponseBody Comment show(@PathVariable int property_id, @PathVariable int id) {
		return commentService.getCommentByRentalUnitID(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment/{id}", method = RequestMethod.PUT)
	public @ResponseBody Comment update(@PathVariable int property_id, @PathVariable int id, @RequestBody Comment comment) {
	    Comment old=commentService.getCommentByRentalUnitID(id);
	    old.setBody(comment.getBody());
		return commentService.updateComment(old);
	}
	
	/**
	 * Delete the specified resource.
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{property_id}/Comment/{id}", method = RequestMethod.DELETE)
	  public void delete(@PathVariable int property_id, @PathVariable int id) {
	    commentService.deleteComment(id);
	  }
	
}
