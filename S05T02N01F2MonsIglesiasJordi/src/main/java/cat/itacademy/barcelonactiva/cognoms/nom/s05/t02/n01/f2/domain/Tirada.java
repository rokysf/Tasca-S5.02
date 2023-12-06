package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tirada {

	@Id
	private String id;
	
	private int valorDau1;
	private int valorDau2;
	private boolean guanyada;
	
	@DBRef
	private Jugador jugador;
	
	
	
	
}

