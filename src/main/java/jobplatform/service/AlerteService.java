package jobplatform.service;




import org.springframework.stereotype.Service;

import jobplatform.model.Alerte;
import jobplatform.repository.AlerteRepository;

@Service
public class AlerteService {

    private final AlerteRepository alerteRepository;

    public AlerteService(AlerteRepository alerteRepository) {
        this.alerteRepository = alerteRepository;
    }

    public Alerte addAlerte(Alerte alerte) {
        return alerteRepository.save(alerte);
    }
}
