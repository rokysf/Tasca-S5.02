package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.model.domain.Tirada;

@Repository
public interface TiradaRepository extends JpaRepository<Tirada, Long> {
	List<Tirada> findByJugador(Jugador jugador);
	void deleteByJugador(Jugador jugador);

}
