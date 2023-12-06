package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.service;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.AuthResponse;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.AuthenticationRequest;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.RegisterRequest;

public interface IAuthService {

	AuthResponse register (RegisterRequest request);
	AuthResponse authenticate (AuthenticationRequest request);
}
