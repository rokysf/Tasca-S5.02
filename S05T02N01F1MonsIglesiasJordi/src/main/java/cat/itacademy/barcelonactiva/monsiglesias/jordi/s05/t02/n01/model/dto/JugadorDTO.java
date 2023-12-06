package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.dto;

import java.time.LocalDateTime;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Jugador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorDTO {

	private Long id;
	private String nomUsuari;
	private LocalDateTime dataRegistre;
	private Float porcentatgeExit;

	
	public static JugadorDTO toJugadorDTO(Jugador jugador) {
		
		JugadorDTO dto = JugadorDTO.builder()
				.id(jugador.getId())
				.nomUsuari(jugador.getNomUsuari())
				.dataRegistre(jugador.getDataRegistre())
				.build();
	
		return dto;
	}

}
