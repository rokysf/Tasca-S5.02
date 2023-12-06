package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {

	String getUserName(String jwt);
	String generateToken(UserDetails userDetails);
	boolean validateToken(String jwt, UserDetails userDetails);
	
}
