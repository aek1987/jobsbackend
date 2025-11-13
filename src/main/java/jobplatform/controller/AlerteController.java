package jobplatform.controller;

import jobplatform.model.Alerte;
import jobplatform.service.AlerteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertes")
@CrossOrigin(origins = "*")
public class AlerteController {

    private final AlerteService alerteService;

    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @GetMapping
    public ResponseEntity<List<Alerte>> getAlertes() {
        return ResponseEntity.ok(alerteService.getAllAlertes());
    }
    @PostMapping
    public ResponseEntity<?> createAlerte(@RequestBody Alerte alerte) {
        // Vérifier le nombre d'alertes pour cet email
        long count = alerteService.countAlertesByEmail(alerte.getEmail());

        if (count >= 3) {
            return ResponseEntity
                    .badRequest()
                    .body("Nombre maximal d'alertes atteint (3) pour cet email");
        }

        return ResponseEntity.ok(alerteService.addAlerte(alerte));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Alerte> updateAlerte(@PathVariable Long id, @RequestBody Alerte alerte) {
        alerte.setId(id);
        return ResponseEntity.ok(alerteService.updateAlerte(alerte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlerte(@PathVariable Long id) {
        alerteService.deleteAlerte(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<?> createAlerte1(@RequestBody Alerte alerte) {
        // Vérifier le nombre d'alertes pour cet email
        long count = alerteService.countAlertesByEmail(alerte.getEmail());

        if (count >= 3) {
            return ResponseEntity
                    .badRequest()
                    .body("Nombre maximal d'alertes atteint (3) pour cet email");
        }

        return ResponseEntity.ok(alerteService.addAlerte(alerte));
    }

}
