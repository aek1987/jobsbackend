package jobplatform.service;



import jobplatform.model.Entreprise;
import jobplatform.repository.EntrepriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public List<Entreprise> getAll() {
        return entrepriseRepository.findAll();
    }

    public Optional<Entreprise> getById(Long id) {
        return entrepriseRepository.findById(id);
    }

    public Entreprise save(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    public void delete(Long id) {
        entrepriseRepository.deleteById(id);
    }
    
    public Optional<Entreprise> getByEmail(String email) {
        return entrepriseRepository.findByEmail(email);
    }

}
