package mk.ukim.finki.roomie.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;
    

    public TokenAuthenticationService() {
    }

    public String addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        String token = tokenHandler.createTokenForUser(user);
        response.addHeader(AUTH_HEADER_NAME, token);
        return token;
    }

    public UserAuthentication getAuthentication(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token=null;
        if(header!=null && header.startsWith("Bearer ")){
        	token=header.substring(7);
        }
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            mk.ukim.finki.roomie.model.User originalUser = tokenHandler.getUser(user.getUsername());
            if (user != null) {
                return new UserAuthentication(user,originalUser);
            }
        }
        return null;
    }
}