package jobplatform.repository;



import jobplatform.model.Candidat;

import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	// Recherche par email insensible à la casse
    @Query("SELECT c FROM Candidat c WHERE LOWER(c.email) = LOWER(:email)")
    Optional<Candidat> findByEmail(@Param("email") String email);
}
