package jobplatform.controller;



import jobplatform.model.Entreprise;
import jobplatform.service.EntrepriseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entreprises")
@CrossOrigin(origins = "*")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping
    public List<Entreprise> getAll() {
        return entrepriseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> getById(@PathVariable Long id) {
        return entrepriseService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entreprise> create(@RequestBody Entreprise entreprise) {
        Entreprise saved = entrepriseService.save(entreprise);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<Entreprise> getByEmail(@PathVariable String email) {
        return entrepriseService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }   
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Entreprise> update(@PathVariable Long id, @RequestBody Entreprise updated) {
        return entrepriseService.getById(id)
                .map(existing -> {
                    existing.setUsername(updated.getUsername() != null ? updated.getUsername() : existing.getUsername());
                    existing.setEmail(updated.getEmail() != null ? updated.getEmail() : existing.getEmail());
                    existing.setPhone(updated.getPhone() != null ? updated.getPhone() : existing.getPhone());
                    existing.setSecteur(updated.getSecteur() != null ? updated.getSecteur() : existing.getSecteur());
                    existing.setDescription(updated.getDescription() != null ? updated.getDescription() : existing.getDescription());
                    existing.setSite(updated.getSite() != null ? updated.getSite() : existing.getSite());
                    existing.setStatus(updated.getStatus() != null ? updated.getStatus() : existing.getStatus());

                    return ResponseEntity.ok(entrepriseService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Entreprise> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return entrepriseService.getById(id)
                .map(existing -> {
                    existing.setStatus(body.get("status"));
                    return ResponseEntity.ok(entrepriseService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
