package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.dto.JugadorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Jugador {

	@Id
	private String id;
	
	private String nomUsuari;
	private LocalDateTime dataRegistre;
	
	
	
	
	
	public static Jugador toJugador(JugadorDTO jugadorDTO) {
		
		return Jugador.builder()
				.id(jugadorDTO.getId())
				.nomUsuari(jugadorDTO.getNomUsuari())
				.dataRegistre(jugadorDTO.getDataRegistre())
				.build();
	}
	
}
