package cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.monsiglesias.jordi.s05.t02.n01.f3.security.domain.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findUserEntityByUserName(String userName);

}
