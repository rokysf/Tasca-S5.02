package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.domain;

import java.time.LocalDateTime;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.dto.JugadorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Builder
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Jugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nomJugador;
	@Column
	private LocalDateTime dataRegistre;
		
	
	
	public static Jugador toJugador(JugadorDTO jugadorDTO) {
		
		return Jugador.builder()
				.id(jugadorDTO.getId())
				.nomJugador(jugadorDTO.getNomUsuari())
				.dataRegistre(jugadorDTO.getDataRegistre())
				.build();
	}
	
}
