package jobplatform.controller;

import jobplatform.model.Candidat;
import jobplatform.model.Offre;
import jobplatform.service.CandidatService;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> addFavori(@PathVariable Long id, @PathVariable Long offreId) {
        boolean added = candidatService.addFavori(id, offreId);
        if (added) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 si déjà existant
        }
    }


    @DeleteMapping("/{id}/favoris/{offreId}")
    public void removeFavori(@PathVariable Long id, @PathVariable Long offreId) {
        candidatService.removeFavori(id, offreId);
    }
 // Vérifie si une offre est favorite pour un candidat
    @GetMapping("/{id}/favoris/{offreId}/isFavorite")
    public ResponseEntity<Boolean> isFavori(
            @PathVariable Long id,
            @PathVariable Long offreId) {

        return candidatService.getById(id)
                .map(candidat -> {
                    boolean exists = candidat.getFavoris() != null && candidat.getFavoris().contains(offreId);
                    return ResponseEntity.ok(exists);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
