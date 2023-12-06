package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.dto;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@Email
	private String email;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String password;
		
	private Role role;

}
