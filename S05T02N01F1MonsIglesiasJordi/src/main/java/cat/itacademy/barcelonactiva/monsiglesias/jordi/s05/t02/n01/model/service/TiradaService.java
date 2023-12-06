package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Tirada;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.repository.TiradaRepository;

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
