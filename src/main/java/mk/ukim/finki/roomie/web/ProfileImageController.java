package mk.ukim.finki.roomie.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "public/api/User")
public class ProfileImageController {
	
	@ResponseBody
    @RequestMapping(value = "/{id}/ProfileImage")
    public String store(@PathVariable int id, @RequestParam(value = "photo", required = false) MultipartFile multipartFile,
            HttpServletResponse httpServletResponse) {

        String orgName = multipartFile.getOriginalFilename();
        System.out.println(orgName);

//        String filePath = "/my_uploads/" + orgName;
//        File dest = new File(filePath);
//        try {
//            multipartFile.transferTo(dest);
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//            return "File uploaded failed:" + orgName;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "File uploaded failed:" + orgName;
//        }
        return "File uploaded:" + orgName;
    }
}
