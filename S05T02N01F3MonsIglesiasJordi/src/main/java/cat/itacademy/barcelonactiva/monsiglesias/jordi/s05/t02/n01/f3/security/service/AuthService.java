package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.domain.UserEntity;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.AuthResponse;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.AuthenticationRequest;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto.RegisterRequest;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

	@Autowired
	private UserEntityRepository userEntityRepository;
	@Autowired
	private IJwtService jwtService;

	private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;

	@Override
	public AuthResponse register(RegisterRequest request) {
		Optional<UserEntity> userFind = userEntityRepository.findUserEntityByUserName(request.getUserName());
		if (!userFind.isEmpty()) {
			return AuthResponse.builder().errorMessage("Aquest nom d'usuari ja existeix, introdueix un nom diferent").build();
		} else {
			UserEntity user = UserEntity.builder().firstName(request.getFirstName()).lastName(request.getLastName())
					.email(request.getEmail()).userName(request.getUserName())
					.password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
			userEntityRepository.save(user);
			String jwtToken = jwtService.generateToken(user);
			return AuthResponse.builder().token(jwtToken).build();
		}
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		try{
			authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			UserEntity user = userEntityRepository.findUserEntityByUserName(request.getUserName()).orElseThrow();
			String jwtToken = jwtService.generateToken(user);
			return AuthResponse.builder().token(jwtToken).build();
		} catch (AuthenticationException e) {
			return AuthResponse.builder().errorMessage("Nom d'usuari o contrasenya incorrectes").build();
		}
	}	
}
