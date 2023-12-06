package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.model.domain.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long>{
	Optional<Jugador> findByNomJugador(String nomJugador);
	
	
	
}
