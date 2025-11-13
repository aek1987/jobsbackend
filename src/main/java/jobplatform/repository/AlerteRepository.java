package jobplatform.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jobplatform.model.Alerte;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {
	
	 long countByEmail(String email);  // compter les alertes par email
}
