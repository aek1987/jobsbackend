package jobplatform.repository;



import jobplatform.model.Candidature;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	
	 // Vérifie si une candidature existe pour un candidat et une offre
    boolean existsByCandidatRefIdAndOffreId(Long candidatRefId, Long offreId);

    // Optionnel : récupérer la candidature existante
    Optional<Candidature> findByCandidatRefIdAndOffreId(Long candidatRefId, Long offreId);
    
    
    List<Candidature> findByCandidatRefId(Long refId);
}
