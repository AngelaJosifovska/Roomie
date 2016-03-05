package mk.ukim.finki.roomie.auth;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import com.google.common.base.Preconditions;

import mk.ukim.finki.roomie.service.UserService;
import mk.ukim.finki.roomie.service.implementation.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenHandler {

    @Autowired
    private UserService userService;

    public TokenHandler() {
    }

    public User parseUserFromToken(String token) {
    	
        String username = Jwts.parser()
                .setSigningKey("b2ZAvZjlNqbOzIbl5738cOvaYxxDGcH8")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        User user=(User) userService.loadUserByUsername(username);
        
        return user;
    }

    public String createTokenForUser(User user) {
    	Date currentTime = new Date();
    	Calendar expirationTime = Calendar.getInstance();
    	expirationTime.setTime(currentTime);
    	expirationTime.add(Calendar.HOUR, 2);
        String token= Jwts.builder()
        		.setExpiration(expirationTime.getTime())
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, "b2ZAvZjlNqbOzIbl5738cOvaYxxDGcH8")
                .compact();
        return token;
    }
    public mk.ukim.finki.roomie.model.User getUser(String username){
    	return userService.getUserByUsername(username);
    }
}
