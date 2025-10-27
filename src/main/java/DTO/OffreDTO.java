package DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffreDTO {
    private Long id;
    private String poste;
    private String localisation;
    private Double salaire;
    private String contrat;
    private String status;
    private LocalDate datePublication;
}
