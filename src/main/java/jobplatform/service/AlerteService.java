package jobplatform.service;

import jobplatform.model.Alerte;
import jobplatform.repository.AlerteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlerteService {

    private final AlerteRepository alerteRepository;

    public AlerteService(AlerteRepository alerteRepository) {
        this.alerteRepository = alerteRepository;
    }

    public List<Alerte> getAllAlertes() {
        return alerteRepository.findAll();
    }

    public Alerte addAlerte(Alerte alerte) {
        return alerteRepository.save(alerte);
    }

    public Alerte updateAlerte(Alerte alerte) {
        return alerteRepository.save(alerte);
    }

    public void deleteAlerte(Long id) {
        Alerte alerte = alerteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerte non trouvée avec id " + id));
        alerteRepository.delete(alerte);
    }

    public long countAlertes() {
        return alerteRepository.count();
    }
    public long countAlertesByEmail(String email) {
        return alerteRepository.countByEmail(email);
    }
    

}
