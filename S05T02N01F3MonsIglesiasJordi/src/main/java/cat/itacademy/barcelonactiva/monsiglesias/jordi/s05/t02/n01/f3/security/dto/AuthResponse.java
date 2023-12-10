package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String token;
	private String errorMessage;
}
