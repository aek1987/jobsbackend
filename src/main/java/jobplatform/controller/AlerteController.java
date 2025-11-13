package jobplatform.controller;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jobplatform.model.Alerte;
import jobplatform.service.AlerteService;

@RestController
@RequestMapping("/api/alertes")
@CrossOrigin(origins = "*") // si Angular est sur un autre port
public class AlerteController {

    private final AlerteService alerteService;

    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @PostMapping
    public ResponseEntity<Alerte> createAlerte(@RequestBody Alerte alerte) {
        try {
            Alerte savedAlerte = alerteService.addAlerte(alerte);
            return ResponseEntity.ok(savedAlerte);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
