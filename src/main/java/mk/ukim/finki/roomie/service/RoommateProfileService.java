package mk.ukim.finki.roomie.service;

import mk.ukim.finki.roomie.model.RoommateProfile;
import mk.ukim.finki.roomie.model.User;

public interface RoommateProfileService {
	
	public RoommateProfile getSingleRoommateProfile(int userID);
	
	public User storeRoomateProfile(RoommateProfile roommateProfile);
	
	public User updateRoomateProfile(RoommateProfile roommateProfile);

}
