package mk.ukim.finki.roomie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.repository.RentalUnitRepository;
import mk.ukim.finki.roomie.service.RentalUnitService;

@Service
public class RentalUnitServiceImpl implements RentalUnitService{

	@Autowired
	RentalUnitRepository rentalUnitRepository;
	
	public RentalUnit getRentalUnitById(Long id) {
		// TODO Auto-generated method stub
		return rentalUnitRepository.getRentalUnitById(id);
	}

	public List<RentalUnit> getAllRentalUnits() {
		// TODO Auto-generated method stub
		return rentalUnitRepository.findAll();
	}

	public RentalUnit storeRentalUnit(RentalUnit rentalUnit) {
		// TODO Auto-generated method stub
		return rentalUnitRepository.saveOrUpdate(rentalUnit);
	}

	public RentalUnit updateRentalUnit(RentalUnit rentalUnit) {
		// TODO Auto-generated method stub
		return rentalUnitRepository.saveOrUpdate(rentalUnit);
	}

}
