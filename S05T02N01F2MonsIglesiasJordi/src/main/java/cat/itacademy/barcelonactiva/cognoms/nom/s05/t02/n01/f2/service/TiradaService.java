package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Tirada;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.repository.TiradaRepository;



@Service
public class TiradaService implements ITiradaService {

	@Autowired
	TiradaRepository tiradaRepository;
	
	@Override
	public Tirada tiradaDaus(Jugador jugador) {
		Tirada novaTirada = new Tirada();
		Random random = new Random();
		novaTirada.setJugador(jugador);
		novaTirada.setValorDau1(random.nextInt(6) + 1);
		novaTirada.setValorDau2(random.nextInt(6) + 1);
		if (novaTirada.getValorDau1() + novaTirada.getValorDau2() == 7) {
			novaTirada.setGuanyada(true);
		} else {
			novaTirada.setGuanyada(false);
		}
		return tiradaRepository.save(novaTirada);
	}

	@Override
	public List<Tirada> llistarTiradesByJugador(Jugador jugador) {
		List<Tirada> list = tiradaRepository.findByJugador(jugador);
		return list;
	}

	@Override
	@Transactional
	public void deleteTiradesByJugador(Jugador jugador) {
		tiradaRepository.deleteByJugador(jugador);
	}

}

