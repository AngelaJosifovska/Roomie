package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.RentalUnit;


public interface RentalUnitService {

    public RentalUnit getRentalUnitById(Integer id);
	
	public List<RentalUnit> getAllRentalUnits();
	
	public List<RentalUnit> getAllRentalUnits(int page, int maxResults);
	
	public Long getTotal();
	
	public RentalUnit storeRentalUnit(RentalUnit rentalUnit);
	
	public RentalUnit updateRentalUnit(RentalUnit rentalUnit);
	
	public List<CategoryFrequency> rentalUnitsGroupBy(String category);
	
	
}
