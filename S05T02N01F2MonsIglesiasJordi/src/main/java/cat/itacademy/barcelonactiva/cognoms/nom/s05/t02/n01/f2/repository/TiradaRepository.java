package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Jugador;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Tirada;


@Repository
public interface TiradaRepository extends MongoRepository<Tirada, Long> {
	List<Tirada> findByJugador(Jugador jugador);
	void deleteByJugador(Jugador jugador);

}
