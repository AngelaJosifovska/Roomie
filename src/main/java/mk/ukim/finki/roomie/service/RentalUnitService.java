package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.model.RentalUnit;


public interface RentalUnitService {

    public RentalUnit getRentalUnitById(Long id);
	
	public List<RentalUnit> getAllRentalUnits();
	
	public RentalUnit storeRentalUnit(RentalUnit rentalUnit);
	
	public RentalUnit updateRentalUnit(RentalUnit rentalUnit);
}
