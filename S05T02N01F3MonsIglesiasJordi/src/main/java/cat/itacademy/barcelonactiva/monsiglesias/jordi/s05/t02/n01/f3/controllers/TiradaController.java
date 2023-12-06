package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.domain.Jugador;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.domain.Tirada;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.service.IJugadorService;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.service.ITiradaService;

@RestController
@RequestMapping("/players")
public class TiradaController {

	@Autowired
	private ITiradaService tiradaService;
	@Autowired
	private IJugadorService jugadorService;

	@PostMapping("{id}/games")
	public ResponseEntity<Object> tiradaDaus(@PathVariable("id") Long id) {
		Jugador jugadorTrobat = jugadorService.getJugadorbyId(id);
		if (jugadorTrobat == null) {
			String missatge = "Aquest id de jugador no existeix, introdueix un id diferent";
			return new ResponseEntity<>(missatge, HttpStatus.NOT_FOUND);
		} else {
			Tirada novaTirada = tiradaService.tiradaDaus(jugadorTrobat);
			return new ResponseEntity<>(novaTirada, HttpStatus.OK);
		}
	}

	@GetMapping("{id}/games")
	public ResponseEntity<Object> llistaTiradesByJugador(@PathVariable("id") Long id) {
		Jugador jugadorTrobat = jugadorService.getJugadorbyId(id);
		if (jugadorTrobat == null) {
			String missatge = "Aquest id de jugador no existeix, introdueix un id diferent";
			return new ResponseEntity<>(missatge, HttpStatus.NOT_FOUND);
		} else {
			List<Tirada> tiradesJugador = tiradaService.llistarTiradesByJugador(jugadorTrobat);
			return tiradesJugador.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
					: new ResponseEntity<>(tiradesJugador, HttpStatus.OK);
		}
	}

	@DeleteMapping("{id}/games")
	public ResponseEntity<String> deleteTiradesByJugador(@PathVariable("id") Long id){
		Jugador jugadorTrobat = jugadorService.getJugadorbyId(id);
		if (jugadorTrobat == null) {
			String missatge = "Aquest id de jugador no existeix, introdueix un id diferent";
			return new ResponseEntity<>(missatge, HttpStatus.NOT_FOUND);
		} 
		
		List<Tirada> tiradesJugador = tiradaService.llistarTiradesByJugador(jugadorTrobat);
	    if (tiradesJugador.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }else {
			tiradaService.deleteTiradesByJugador(jugadorTrobat);
			String missatge = "Tirades esborrades del jugador amb el id " + jugadorTrobat.getId();
			return new ResponseEntity<>(missatge, HttpStatus.OK);
		}
	}

}
