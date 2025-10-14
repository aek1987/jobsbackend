package jobplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jobplatform.model.Offre;



@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
}
