package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.domain.Jugador;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.domain.Tirada;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.repository.TiradaRepository;

@Service
public class JugadorService implements IJugadorService {

	@Autowired
	private JugadorRepository jugadorRepository;
	@Autowired
	private TiradaRepository tiradaRepository;

	@Override
	public Jugador getJugadorbyId(Long id) {
		Optional<Jugador> jugador = jugadorRepository.findById(id);
		if (jugador.isEmpty()) {
			return null;
		} else {
			return jugador.get();
		}

	}

	@Override
	public Jugador getJugadorbyName(String nomJugador) {
		Optional<Jugador> jugador = jugadorRepository.findByNomJugador(nomJugador);
		if (jugador.isEmpty()) {
			return null;
		} else {
			return jugador.get();
		}

	}

	@Override
	public Jugador createJugador(Jugador jugador) {
		jugador.setId(null);
		if (jugador.getNomJugador().isEmpty() || jugador.getNomJugador().equalsIgnoreCase("ANONIM")) {
			jugador.setNomJugador("ANONIM");
			return jugadorRepository.save(jugador);
		} else {
			Jugador jugadorTrobat = this.getJugadorbyName(jugador.getNomJugador());
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
		if (jugador.getNomJugador().isEmpty() || jugador.getNomJugador().equalsIgnoreCase("ANONIM")) {
			jugadorTrobatId.setNomJugador("ANONIM");
			jugadorRepository.save(jugadorTrobatId);
			return false;
		}
		Jugador jugadorTrobatNom = this.getJugadorbyName(jugador.getNomJugador());
		if (jugadorTrobatNom != null) {
			return true;
		}
		jugadorTrobatId.setNomJugador(jugador.getNomJugador());
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
