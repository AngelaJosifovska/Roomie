package mk.ukim.finki.roomie.service.implementation;

import java.util.List;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.repository.RentalUnitRepository;
import mk.ukim.finki.roomie.service.RentalUnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalUnitServiceImpl implements RentalUnitService{

	@Autowired
	private RentalUnitRepository rentalUnitRepository;
	
	public RentalUnit getRentalUnitById(Integer id) {
		return rentalUnitRepository.getRentalUnitById(id);
	}

	public List<RentalUnit> getAllRentalUnits() {
		return rentalUnitRepository.findAll();
	}

	public RentalUnit storeRentalUnit(RentalUnit rentalUnit) {
		return rentalUnitRepository.saveOrUpdate(rentalUnit);
	}

	public RentalUnit updateRentalUnit(RentalUnit rentalUnit) {
		return rentalUnitRepository.saveOrUpdate(rentalUnit);
	}

	public List<RentalUnit> getAllRentalUnits(int page, int maxResults) {
		return rentalUnitRepository.findAll(page,maxResults);
	}

	public Long getTotal() {
		return rentalUnitRepository.getTotal();
	}

	public List<CategoryFrequency> rentalUnitsGroupBy(String category) {
		return rentalUnitRepository.rentalUnitsGroupBy(category);	
	}

}
