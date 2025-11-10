package jobplatform.repository;



import jobplatform.model.Entreprise;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	
	Optional<Entreprise> findByEmail(String email);

}
