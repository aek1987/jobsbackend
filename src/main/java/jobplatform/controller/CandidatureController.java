package jobplatform.controller;

import jobplatform.model.Candidature;
import jobplatform.service.CandidatureService;
import jobplatform.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/candidatures")
@CrossOrigin(origins = "*")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private CandidatureRepository candidatureRepository;

    // ✅ Ajouter une candidature
    @PostMapping("/add")
    public Candidature addCandidature(
            @RequestParam Long offreId,
            @RequestParam Long candidatId,
            @RequestParam(required = false) String message) {
        return candidatureService.addCandidature(offreId, candidatId, message);
    }

    // ✅ Récupérer toutes les candidatures d’un candidat (sans pagination)
    @GetMapping("/candidat/{candidatId}")
    public List<Candidature> getCandidaturesByCandidat(@PathVariable Long candidatId) {
        return candidatureService.getCandidaturesByCandidat(candidatId);
    }

    // ✅ Récupérer les candidatures d’un candidat (RefId) avec pagination
    @GetMapping("/candidat/ref/{refId}")
    public Map<String, Object> getCandidaturesByCandidatPaginated(
            @PathVariable Long refId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("dateCandidature").descending());
        Page<Candidature> pageResult = candidatureRepository.findByCandidatRefId(refId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("content", pageResult.getContent());
        response.put("currentPage", pageResult.getNumber());
        response.put("totalItems", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());

        return response;
    }
}
