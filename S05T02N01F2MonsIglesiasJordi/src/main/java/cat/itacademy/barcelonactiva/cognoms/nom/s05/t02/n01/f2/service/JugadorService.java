package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Tirada;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.repository.TiradaRepository;

@Service
public class JugadorService implements IJugadorService {

	@Autowired
	JugadorRepository jugadorRepository;
	@Autowired
	TiradaRepository tiradaRepository;

	@Override
	public Jugador getJugadorbyId(String id) {
		Optional<Jugador> jugador = jugadorRepository.findById(id);
		if (jugador.isEmpty()) {
			return null;
		} else {
			return jugador.get();
		}

	}

	@Override
	public Jugador getJugadorbyName(String nomUsuari) {
		Optional<Jugador> jugador = jugadorRepository.findByNomUsuari(nomUsuari);
		if (jugador.isEmpty()) {
			return null;
		} else {
			return jugador.get();
		}

	}

	@Override
	public Jugador createJugador(Jugador jugador) {
		jugador.setId(null);
		if (jugador.getNomUsuari().isEmpty() || jugador.getNomUsuari().equalsIgnoreCase("ANONIM")) {
			jugador.setNomUsuari("ANONIM");
			return jugadorRepository.save(jugador);
		} else {
			Jugador jugadorTrobat = this.getJugadorbyName(jugador.getNomUsuari());
			if (jugadorTrobat == null) {
				return jugadorRepository.save(jugador);
			} else {
				return null;
			}
		}
	}

	@Override
	public Boolean updateJugador(Jugador jugador) {
		Jugador jugadorTrobatId = this.getJugadorbyId(jugador.getId());
		if (jugadorTrobatId == null) {
			return null;
		}
		if (jugador.getNomUsuari().isEmpty() || jugador.getNomUsuari().equalsIgnoreCase("ANONIM")) {
			jugadorTrobatId.setNomUsuari("ANONIM");
			jugadorRepository.save(jugadorTrobatId);
			return false;
		}
		Jugador jugadorTrobatNom = this.getJugadorbyName(jugador.getNomUsuari());
		if (jugadorTrobatNom != null) {
			return true;
		}
		jugadorTrobatId.setNomUsuari(jugador.getNomUsuari());
		jugadorRepository.save(jugadorTrobatId);
		return false;
	}

	@Override
	public List<JugadorDTO> listAllJugadors() {
		List<Jugador> llistaJugadors = jugadorRepository.findAll();
		List<JugadorDTO> llistaJugadorsDTO = new ArrayList<>();

		for (Jugador jugador : llistaJugadors) {

			Float porcentatgeExit = calcularPorcentatgeExit(jugador);
			JugadorDTO jugadorDTO = new JugadorDTO();
			jugadorDTO = JugadorDTO.toJugadorDTO(jugador);
			jugadorDTO.setPorcentatgeExit(porcentatgeExit);
			llistaJugadorsDTO.add(jugadorDTO);
		}

		return llistaJugadorsDTO;

	}

	@Override
	public Float calcularPorcentatgeExit(Jugador jugador) {
		List<Tirada> llistaTirades = tiradaRepository.findByJugador(jugador);
		int contadorGuanyades = 0;

		for (Tirada tirada : llistaTirades) {
			if (tirada.isGuanyada()) {
				contadorGuanyades++;
			}
		}

		int contadorTotal = llistaTirades.size();
		float porcentatgeExit = (float) contadorGuanyades / (float) contadorTotal * 100;

		return porcentatgeExit;

	}

	@Override
	public Float rankingMigJugadors() {
		List<JugadorDTO> llistaJugadors = this.listAllJugadors();
		float exitTotal = 0.0f;
		for (JugadorDTO jugadorDTO : llistaJugadors) {
			if (jugadorDTO.getPorcentatgeExit().isNaN()) {
				jugadorDTO.setPorcentatgeExit(0.0f);
			}
			exitTotal = exitTotal + jugadorDTO.getPorcentatgeExit();
		}
		float exitMig = exitTotal / (float) llistaJugadors.size();
		return exitMig;
	}

	@Override
	public JugadorDTO jugadorLoser() {
		List<JugadorDTO> llistaJugadors = this.listAllJugadors();
		JugadorDTO jugadorLoser = llistaJugadors.get(0);
		for (JugadorDTO jugadorDTO : llistaJugadors) {
			if (jugadorDTO.getPorcentatgeExit() < jugadorLoser.getPorcentatgeExit()) {
				jugadorLoser = jugadorDTO;
			}
		}
		return jugadorLoser;
	}

	@Override
	public JugadorDTO jugadorWinner() {
		List<JugadorDTO> llistaJugadors = this.listAllJugadors();
		JugadorDTO jugadorWinner = llistaJugadors.get(0);
		for (JugadorDTO jugadorDTO : llistaJugadors) {
			if (jugadorDTO.getPorcentatgeExit() > jugadorWinner.getPorcentatgeExit()) {
				jugadorWinner = jugadorDTO;
			}
		}
		return jugadorWinner;
	}

}
