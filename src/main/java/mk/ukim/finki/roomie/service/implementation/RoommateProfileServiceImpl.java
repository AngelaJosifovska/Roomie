package mk.ukim.finki.roomie.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.RoommateProfile;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.repository.RoommateProfileRepository;
import mk.ukim.finki.roomie.service.RoommateProfileService;

@Service
public class RoommateProfileServiceImpl implements RoommateProfileService {
	
	@Autowired
	private RoommateProfileRepository roomateProfileRepository;

	public RoommateProfile getSingleRoommateProfile(int userID) {
		RoommateProfile rp = roomateProfileRepository.findByUserID(userID);
		rp.setUser_id(userID);
		return rp;
	}

	public User storeRoomateProfile(RoommateProfile roommateProfile) {
		return roomateProfileRepository.saveOrUpdate(roommateProfile);
	}

	public User updateRoomateProfile(RoommateProfile roommateProfile) {
		return roomateProfileRepository.saveOrUpdate(roommateProfile);
	}

}
