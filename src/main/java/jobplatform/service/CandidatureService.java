package jobplatform.service;



import jobplatform.model.*;
import jobplatform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private OffreRepository offreRepository;

    public Candidature addCandidature(Long offreId, Long candidatId, String message) {

        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offre introuvable"));
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new RuntimeException("Candidat introuvable"));

        // ✅ Vérifier si le candidat a déjà postulé à cette offre
        boolean dejaPostule = candidatureRepository.existsByCandidatRefIdAndOffreId(candidatId, offreId);
        if (dejaPostule) {
            throw new RuntimeException("Vous avez déjà postulé à cette offre !");
        }

        Candidature candidature = Candidature.builder()
                .offre(offre)
                .candidat(candidat)
                .dateCandidature(LocalDateTime.now())
                .statut("en attente")
                .message(message)
                .build();

        return candidatureRepository.save(candidature);
    }
    public List<Candidature> getCandidaturesByCandidat(Long candidatId) {
        return candidatureRepository.findByCandidatRefId(candidatId);
    }

}
