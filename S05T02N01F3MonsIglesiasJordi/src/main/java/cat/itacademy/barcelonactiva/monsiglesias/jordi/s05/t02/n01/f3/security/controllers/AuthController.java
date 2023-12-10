package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.AuthResponse;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.AuthenticationRequest;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.RegisterRequest;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.service.IAuthService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private IAuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
		AuthResponse authresponse = authService.register(request);
		if(authresponse.getErrorMessage() != null) {
			return new ResponseEntity<>(authresponse.getErrorMessage(), HttpStatus.CONFLICT);
		}else {
			return new ResponseEntity<>(authresponse.getToken(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
		AuthResponse authResponse = authService.authenticate(request);
		if(authResponse.getErrorMessage() == null) {
			return ResponseEntity.ok(authResponse.getToken());
		} else {
			return new ResponseEntity<>(authResponse.getErrorMessage(), HttpStatus.UNAUTHORIZED); 
		}
	}
	
}
