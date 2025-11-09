package jobplatform.controller;

import jobplatform.model.Candidat;
import jobplatform.model.Offre;
import jobplatform.service.CandidatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidats")
@CrossOrigin(origins = "*")
public class CandidatController {

    private final CandidatService candidatService;

    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    @GetMapping
    public List<Candidat> getAll() {
        return candidatService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getById(@PathVariable Long id) {
        return candidatService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // 🔹 Récupérer un candidat par email
    // 🔹 Récupérer un candidat par email
    @GetMapping("/by-email/{email}")
    public ResponseEntity<Candidat> getCandidatByEmail(@PathVariable String email) {
        return candidatService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Candidat> create(@RequestBody Candidat candidat) {
        Candidat saved = candidatService.save(candidat);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> update(@PathVariable Long refId, @RequestBody Candidat updated) {
        return candidatService.getById(refId)
                .map(existing -> {
                    updated.setRefId(refId);
                    return ResponseEntity.ok(candidatService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        candidatService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/favoris")
    public List<Offre> getFavoris(@PathVariable Long id) {
        return candidatService.getFavoris(id);
    }

    @PostMapping("/{id}/favoris/{offreId}")
    public void addFavori(@PathVariable Long id, @PathVariable Long offreId) {
        candidatService.addFavori(id, offreId);
    }

    @DeleteMapping("/{id}/favoris/{offreId}")
    public void removeFavori(@PathVariable Long id, @PathVariable Long offreId) {
        candidatService.removeFavori(id, offreId);
    }
    
}
