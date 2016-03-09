package mk.ukim.finki.roomie.service;

import com.google.maps.model.GeocodingResult;

public interface GoogleGeocoderService {
	
	public GeocodingResult getLocationFromAddress(String address, String city);
}
