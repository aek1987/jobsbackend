package jobplatform.service;



import jobplatform.model.Candidat;
import jobplatform.model.Offre;
import jobplatform.repository.CandidatRepository;
import org.springframework.stereotype.Service;

import comptes.mapper.CandidatMapper;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;
    private final CandidatMapper candidatMapper;

    public CandidatService(CandidatRepository candidatRepository,CandidatMapper candidatMapper) {
        this.candidatRepository = candidatRepository;
        this.candidatMapper = candidatMapper;
    }

    public List<Candidat> getAll() {
        return candidatRepository.findAll();
    }

    public Optional<Candidat> getById(Long id) {
        return candidatRepository.findById(id);
    }

    public Candidat save(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public void delete(Long id) {
        candidatRepository.deleteById(id);
    }
    // ✅ Ajout : récupération par email
    public Optional<Candidat> getByEmail(String email) {
        return candidatRepository.findByEmail(email);
    }
    
    public List<Offre> getFavoris(Long candidatId) {
        return candidatMapper.getFavoris(candidatId);
    }

    public void addFavori(Long candidatId, Long offreId) {
        candidatMapper.addFavori(candidatId, offreId);
    }

    public void removeFavori(Long candidatId, Long offreId) {
        candidatMapper.removeFavori(candidatId, offreId);
    } 
    
}
