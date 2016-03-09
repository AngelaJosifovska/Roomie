package mk.ukim.finki.roomie.service.implementation;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import mk.ukim.finki.roomie.service.GoogleGeocoderService;

@Service
public class GoogleGeocoderServiceImpl implements GoogleGeocoderService {

	public GeocodingResult getLocationFromAddress(String address, String city) {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyC-pggo9JSymdL9cA0WblV4mUdxvidaZKY");
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context, address + ", " + city).await();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return results[0];
	}

	
}
