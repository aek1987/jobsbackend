package jobplatform.service;



import jobplatform.model.Candidat;
import jobplatform.repository.CandidatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;

    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
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
}
