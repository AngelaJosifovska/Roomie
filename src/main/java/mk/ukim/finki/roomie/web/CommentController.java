package mk.ukim.finki.roomie.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

class Comment {
	public String text;

	public Comment(String text) {
		super();
		this.text = text;
	}
}

@RestController
@RequestMapping(value = "public/api/RentalUnit")
public class CommentController {
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment", method = RequestMethod.GET)
	public @ResponseBody List<Comment> index(@PathVariable long property_id) {
		Comment unit = new Comment("Rental unit: " + property_id + " -> Comments");
		List<Comment> list = new ArrayList<Comment>();
		list.add(unit);
		return list;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment", method = RequestMethod.POST)
	public @ResponseBody Comment store(@PathVariable long property_id) {
		Comment unit = new Comment("New comment for: " + property_id);
		return unit;
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment/{id}", method = RequestMethod.GET)
	public @ResponseBody Comment show(@PathVariable long property_id, @PathVariable long id) {
		Comment unit = new Comment("Rental unit number: " + property_id + " -> Comment number: " + id);
		return unit;
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{property_id}/Comment/{id}", method = RequestMethod.PUT)
	public @ResponseBody Comment update(@PathVariable long property_id, @PathVariable long id) {
		Comment unit = new Comment("Updated comment: " + id + " on property " + property_id);
		return unit;
	}

}
