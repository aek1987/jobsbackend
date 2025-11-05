package jobplatform.repository;



import jobplatform.model.Candidat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	 // 🔹 Pour récupérer un candidat via son adresse email
    Optional<Candidat> findByEmail(String email);
}
