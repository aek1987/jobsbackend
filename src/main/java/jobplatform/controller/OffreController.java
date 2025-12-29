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
    
    
    @GetMapping("/filter")
    public ResponseEntity<Page<Offre>> filterOffres(
            @RequestParam(required = false) String secteur,
            @RequestParam(required = false) String contrat,
            @RequestParam(required = false) String localisation,
            @RequestParam(required = false) String teletravail,            
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) Double salaireMin,
            @RequestParam(required = false) Double salaireMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "date_publication") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, Math.min(size, 50), sort);
        Page<Offre> offres = offreService.filterOffres(secteur, contrat, localisation,teletravail, salaireMin,salaireMax,experience, pageable);

        return ResponseEntity.ok(offres);
    }

 
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Offre> getById(@PathVariable Long id) {
        return offreService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offreService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/paged")
    public ResponseEntity<Page<Offre>> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "datePublication") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Offre> offres = offreService.getAllPaged(pageable);
        return ResponseEntity.ok(offres);
    }

    @PostMapping
    public ResponseEntity<Offre> create(@RequestBody Offre offre) {
        Offre saved = offreService.save(offre);
        return ResponseEntity.status(201).body(saved);
    }
 
    
    
    
}
