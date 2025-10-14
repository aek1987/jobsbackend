package jobplatform.controller;

import jobplatform.model.Candidat;
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

    @PostMapping
    public ResponseEntity<Candidat> create(@RequestBody Candidat candidat) {
        Candidat saved = candidatService.save(candidat);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> update(@PathVariable Long id, @RequestBody Candidat updated) {
        return candidatService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(candidatService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        candidatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
