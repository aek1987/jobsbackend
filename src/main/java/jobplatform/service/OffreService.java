package jobplatform.service;

import org.springframework.stereotype.Service;

import jobplatform.model.Offre;
import jobplatform.repository.OffreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {
    private final OffreRepository offreRepository;

    public OffreService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    public List<Offre> getAll() {
        return offreRepository.findAll();
    }

    public Optional<Offre> getById(Long id) {
        return offreRepository.findById(id);
    }

    public Offre save(Offre offre) {
        return offreRepository.save(offre);
    }

    public void delete(Long id) {
        offreRepository.deleteById(id);
    }
}
