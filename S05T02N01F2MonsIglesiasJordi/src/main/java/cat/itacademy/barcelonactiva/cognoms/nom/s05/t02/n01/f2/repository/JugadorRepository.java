package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.n01.f2.domain.Jugador;



@Repository
public interface JugadorRepository extends MongoRepository<Jugador, Long>{
	Optional<Jugador> findByNomUsuari(String nomUsuari);
	Optional<Jugador> findById(String id);
	
	
	
}