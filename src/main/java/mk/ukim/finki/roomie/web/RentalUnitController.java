package mk.ukim.finki.roomie.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

class RentalUnit {
	public String text;

	public RentalUnit(String text) {
		super();
		this.text = text;
	}
}

@RestController
@RequestMapping(value = "public/api/RentalUnit")
public class RentalUnitController {
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody List<RentalUnit> index() {
		RentalUnit unit = new RentalUnit("All rental units");
		List<RentalUnit> list = new ArrayList<RentalUnit>();
		list.add(unit);
		return list;
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody RentalUnit store() {
		RentalUnit unit = new RentalUnit("New rental unit");
		return unit;
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody RentalUnit show(@PathVariable long id) {
		RentalUnit unit = new RentalUnit("Rental unit number: " + id);
		return unit;
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody RentalUnit update(@PathVariable long id) {
		RentalUnit unit = new RentalUnit("Updated property: " + id);
		return unit;
	}

}
