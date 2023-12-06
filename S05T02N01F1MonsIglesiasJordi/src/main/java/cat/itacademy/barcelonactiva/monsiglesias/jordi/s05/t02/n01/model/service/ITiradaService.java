package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.service;

import java.util.List;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Tirada;


public interface ITiradaService {

	Tirada tiradaDaus(Jugador jugador);
	List<Tirada> llistarTiradesByJugador(Jugador jugador);
	void deleteTiradesByJugador(Jugador jugador);
	
	
	
	
}
