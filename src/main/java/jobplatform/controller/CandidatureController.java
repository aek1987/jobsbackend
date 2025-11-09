package jobplatform.controller;


import jobplatform.model.Candidature;
import jobplatform.service.CandidatureService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidatures")
@CrossOrigin(origins = "*")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @PostMapping("/add")
    public Candidature addCandidature(
            @RequestParam Long offreId,
            @RequestParam Long candidatId,
            @RequestParam(required = false) String message) {
        return candidatureService.addCandidature(offreId, candidatId, message);
    }
    
    // ✅ Récupérer toutes les candidatures d’un candidat
    @GetMapping("/{candidatId}")
    public List<Candidature> getCandidaturesByCandidat(@PathVariable Long candidatId) {
        return candidatureService.getCandidaturesByCandidat(candidatId);
    }
}
