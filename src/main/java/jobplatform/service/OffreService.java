package jobplatform.service;

import jobplatform.model.Offre;
import comptes.mapper.OffreMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {

    private final OffreMapper offreMapper;

    public OffreService(OffreMapper offreMapper) {
        this.offreMapper = offreMapper;
    }

    public List<Offre> getAll() {
        return offreMapper.findAll();
    }

    public Optional<Offre> getById(Long id) {
        return Optional.ofNullable(offreMapper.findById(id));
    }

    public Offre save(Offre offre) {
        if (offre.getId() == null || offre.getId() == 0) {
            offreMapper.insert(offre); // L'ID sera rempli automatiquement
        } else {
            offreMapper.update(offre);
        }
        return offre;
    }

    public void delete(Long id) {
        offreMapper.delete(id);
    }

    public Page<Offre> getAllPaged(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<Offre> offres = offreMapper.findAllPaged(pageable.getPageSize(), offset);
        int total = offreMapper.countAll();
        return new PageImpl<>(offres, pageable, total);
    }

    public Page<Offre> filterOffres(String secteur, String contrat, String localisation,String teletravail, Double salaireMin,String experience, Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<Offre> offres = offreMapper.filterOffres(secteur, contrat, localisation,teletravail, salaireMin,experience, pageable.getPageSize(), offset);
        int total = offreMapper.countFiltered(secteur, contrat, localisation, salaireMin,experience);
        return new PageImpl<>(offres, pageable, total);
    }
}
