package mk.ukim.finki.roomie.web;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.service.RentalUnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "public/api/RentalUnit")
public class RentalUnitController {
	
	@Autowired
	RentalUnitService rentalUnitService;
	
	/**
	 * Display a listing of the resources
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody List<RentalUnit> index() {
		return rentalUnitService.getAllRentalUnits();
	}
	
	/**
	 * Store a newly created resource in storage.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody RentalUnit store(@RequestBody RentalUnit rentalUnit) {
		System.out.println(rentalUnit.getUser());
		return rentalUnitService.storeRentalUnit(rentalUnit);
	}
	
	/**
	 * Display the specified resource.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody RentalUnit show(@PathVariable long id) {
		return rentalUnitService.getRentalUnitById(id);
	}
	
	/**
	 * Update the specified resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody RentalUnit update(@PathVariable long id,@RequestBody RentalUnit rentalUnit) {
		return rentalUnitService.updateRentalUnit(rentalUnit);
	}

}
