package com.example.service;

import com.example.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private User user = User.getUser("username","pwd","NO TOKEN", 100000);


    public boolean validateUser(String username, String password){
        /*if (username != user.getPassword()){
            return false;
        }
        if (password != user.getUserName()){
            return false;
        }*/
        return true;
    }

    public User login(String username, String password) {
        if (this.validateUser(username, password)){
            String token = this.getJWTToken((String) username);
            user.setUserName(username);
            user.setToken(token);
        }
        else {
            user.setUserName("NOT VALID USER");
            user.setToken("INVALID USER");
        }
        return user;
    }

    public String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
