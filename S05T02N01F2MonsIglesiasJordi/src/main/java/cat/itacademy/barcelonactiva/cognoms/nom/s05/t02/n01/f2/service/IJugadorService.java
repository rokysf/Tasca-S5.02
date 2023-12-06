package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.service;

import java.util.List;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.dto.JugadorDTO;



public interface IJugadorService {

	Jugador getJugadorbyId(String id);
	Jugador createJugador(Jugador jugador);
	Boolean updateJugador(Jugador jugador);
	Jugador getJugadorbyName(String nom);
	List<JugadorDTO> listAllJugadors();
	Float rankingMigJugadors();
	JugadorDTO jugadorLoser();
	JugadorDTO jugadorWinner();
	Float calcularPorcentatgeExit(Jugador jugador);
	
	
}
