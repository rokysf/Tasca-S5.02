package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.controllers;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.service.IJugadorService;

@RestController
@RequestMapping("/players")
public class JugadorController {

	@Autowired
	private IJugadorService jugadorService;

	@PostMapping
	public ResponseEntity<Object> createJugador(@RequestBody Jugador jugador) {
		jugador.setDataRegistre(LocalDateTime.now());
		Jugador createdJugador = jugadorService.createJugador(jugador);
		if (createdJugador == null) {
			String missatge = "Aquest nom de jugador ja existeix, introdueix un nom diferent";
			return new ResponseEntity<>(missatge, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<>(createdJugador, HttpStatus.CREATED);
		}
	}

	@PutMapping
	public ResponseEntity<Object> updateJugador(@RequestBody Jugador jugador) {
		Boolean resultat = jugadorService.updateJugador(jugador);
		if (resultat==null) {
			String missatge = "Aquest id de jugador no existeix, introdueix un id diferent";
			return new ResponseEntity<>(missatge, HttpStatus.NOT_FOUND);
		} 
		if (resultat==true) {
			String missatge = "Aquest nom de jugador ja existeix, introdueix un nom diferent";
			return new ResponseEntity<>(missatge, HttpStatus.NOT_FOUND);
		}else{  
			Jugador updatedJugador = jugadorService.getJugadorbyId(jugador.getId());
			return new ResponseEntity<>(updatedJugador, HttpStatus.OK);
		}
	}	

	@GetMapping
	public ResponseEntity<List<JugadorDTO>> listJugadors(){
		List<JugadorDTO> llistaJugadorsDTO = jugadorService.listAllJugadors();
		return llistaJugadorsDTO.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(llistaJugadorsDTO ,HttpStatus.OK);
	}
	
	@GetMapping("/ranking")
	public ResponseEntity<String> rankingJugadors(){
		if(jugadorService.listAllJugadors().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			float rankingMig = jugadorService.rankingMigJugadors();
			String missatge = "El ranking mig de tots els jugadors es del "+rankingMig+"%";
			return new ResponseEntity<>(missatge, HttpStatus.OK);
		}
	}	
	
	@GetMapping("/ranking/loser")
	public ResponseEntity<Object> rankingLoser(){
		if(jugadorService.listAllJugadors().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			JugadorDTO jugadorLoser = jugadorService.jugadorLoser();
			return new ResponseEntity<>(jugadorLoser, HttpStatus.OK);
		}
	}	
	
	@GetMapping("/ranking/winner")
	public ResponseEntity<Object> rankingWinner(){
		if(jugadorService.listAllJugadors().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			JugadorDTO jugadorWinner = jugadorService.jugadorWinner();
			return new ResponseEntity<>(jugadorWinner, HttpStatus.OK);
		}
	}	
}
