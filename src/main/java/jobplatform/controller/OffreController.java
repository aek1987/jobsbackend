package jobplatform.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DTO.OffreDTO;
import jobplatform.model.Offre;
import jobplatform.service.OffreService;

import java.util.List;

@RestController
@RequestMapping("/api/offres")

public class OffreController {

    private final OffreService offreService;

    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @GetMapping
    public List<Offre> getAll() {
        return offreService.getAll();
    }
    @GetMapping("/paged")
    public ResponseEntity<Page<OffreDTO>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "datePublication") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        size = Math.min(size, 50);
        page = Math.max(page, 0);

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(page, size, sort);

        Page<OffreDTO> offres = offreService.getAllPagedDTO(pageable);
        return ResponseEntity.ok(offres);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Offre> getById(@PathVariable Long id) {
        return offreService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Offre> create(@RequestBody Offre offre) {
        Offre saved = offreService.save(offre);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offre> update(@PathVariable Long id, @RequestBody Offre updated) {
        return offreService.getById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(offreService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
