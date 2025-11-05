package jobplatform.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DTO.OffreDTO;
import comptes.mapper.OffreMapper;
import jobplatform.model.Entreprise;
import jobplatform.model.Offre;
import jobplatform.repository.OffreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {
      private final OffreMapper offreMapper;

    public OffreService(OffreMapper offreMapper) {
       this.offreMapper = offreMapper;
    }

    // ✅ Récupération de toutes les offres
    public List<Offre> getAll() {
        return offreMapper.findAll();
    }

    // ✅ Récupération d’une offre par ID
    public Optional<Offre> getById(Long id) {
        return Optional.ofNullable(offreMapper.findById(id));
    }

    // ✅ Création d’une nouvelle offre
    public void save(Offre offre) {
        if (offre.getId() == null) {
            offreMapper.insert(offre);
        } else {
            offreMapper.update(offre);
        }
    }

    // ✅ Suppression d’une offre
    public void delete(Long id) {
        offreMapper.delete(id);
    }

    // ✅ Pagination simple
    public Page<Offre> getAllPaged(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<Offre> offres = offreMapper.findAllPaged(pageable.getPageSize(), offset);
        int total = offreMapper.countAll();
        return new PageImpl<>(offres, pageable, total);
    }

    

    public Page<Offre> filterOffres(String secteur, String contrat, String localisation, Double salaireMin, Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<Offre> offres = offreMapper.filterOffres(secteur, contrat, localisation, salaireMin, pageable.getPageSize(), offset);
        int total = offreMapper.countAll(); // tu peux aussi ajouter une version filtrée si besoin
        return new PageImpl<>(offres, pageable, total);
    }
   

}
