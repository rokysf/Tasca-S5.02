package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.service;

import java.util.List;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.domain.Jugador;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.dto.JugadorDTO;

public interface IJugadorService {

	Jugador getJugadorbyId(Long id);
	Jugador createJugador(Jugador jugador);
	Boolean updateJugador(Jugador jugador);
	Jugador getJugadorbyName(String nom);
	List<JugadorDTO> listAllJugadors();
	Float rankingMigJugadors();
	JugadorDTO jugadorLoser();
	JugadorDTO jugadorWinner();
	Float calcularPorcentatgeExit(Jugador jugador);
	
	
}
