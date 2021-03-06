package mk.ukim.finki.roomie.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.roomie.model.ProfileImage;
import mk.ukim.finki.roomie.model.PropertyPicture;
import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.RentalUnitService;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "public/api/")
public class ImageUploadController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RentalUnitService rentalUnitService;
	
	@ResponseBody
    @RequestMapping(value = "User/{id}/ProfileImage")
    public User storeProfileImage(@PathVariable int id, @RequestParam(value = "photo") MultipartFile multipartFile, 
    		HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IllegalStateException, IOException {

		String base_path = httpServletRequest.getSession().getServletContext().getRealPath("/profile_images");
        
        File directory = new File(base_path + "/" + id);
        if(!directory.exists()) {
        	directory.mkdir();
        }
        
        String fileName = new Date().getTime() + "_" + multipartFile.getOriginalFilename();
		String caption = httpServletRequest.getParameter("caption");
		String description = httpServletRequest.getParameter("description");
		String file_location = "/roomie/profile_images/" + id + "/" + fileName;
		
		User user = userService.getUserById(id);
		if(user.getProfile_image() == null) {
			ProfileImage profile_image = new ProfileImage(caption, description, file_location);
			user.setProfile_image(profile_image);
			profile_image.setUser(user);
		} else {
			File oldImage = new File(httpServletRequest.getSession().getServletContext().getRealPath(user.getProfile_image().getLocation().substring(8)));
			oldImage.delete();
			
			user.getProfile_image().setCaption(caption);
			user.getProfile_image().setDescription(description);
			user.getProfile_image().setLocation(file_location);
		}
		
		userService.storeUser(user);
		
        File image_file = new File(directory + "/" + fileName);
        multipartFile.transferTo(image_file);

        return user;
    }
	
	@ResponseBody
    @RequestMapping(value = "RentalUnit/{property_id}/PropertyPicture")
    public RentalUnit storePropertyImage(@PathVariable int property_id, @RequestParam(value = "photo") MultipartFile multipartFile, 
    		HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IllegalStateException, IOException {

		String base_path = httpServletRequest.getSession().getServletContext().getRealPath("/property_images");
        
        File directory = new File(base_path + "/" + property_id);
        if(!directory.exists()) {
        	directory.mkdir();
        }
        
        String fileName = new Date().getTime() + "_" + multipartFile.getOriginalFilename();
		String caption = httpServletRequest.getParameter("caption");
		String file_location = "/roomie/property_images/" + property_id + "/" + fileName;
		
		RentalUnit rentalUnit = rentalUnitService.getRentalUnitById(property_id);
		PropertyPicture property_picture = new PropertyPicture(caption, file_location, file_location);
		property_picture.setRentalUnit(rentalUnit);
		rentalUnit.addPropertyPicture(property_picture);
		rentalUnitService.storeRentalUnit(rentalUnit);
		
        File image_file = new File(directory + "/" + fileName);
        multipartFile.transferTo(image_file);

        return rentalUnit;
    }
}
