package jobplatform.controller;



import jobplatform.model.Entreprise;
import jobplatform.service.EntrepriseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                    updated.setId(id);
                    return ResponseEntity.ok(entrepriseService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
