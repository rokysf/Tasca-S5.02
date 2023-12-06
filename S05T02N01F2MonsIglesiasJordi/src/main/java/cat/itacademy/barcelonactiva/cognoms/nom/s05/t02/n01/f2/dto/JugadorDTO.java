package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.dto;

import java.time.LocalDateTime;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Jugador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JugadorDTO {

	private String id;
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
