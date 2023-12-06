package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.service;

import java.util.List;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Tirada;



public interface ITiradaService {

	Tirada tiradaDaus(Jugador jugador);
	List<Tirada> llistarTiradesByJugador(Jugador jugador);
	void deleteTiradesByJugador(Jugador jugador);
	
	
	
	
}

