package DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffreDTO {
    private Long id;
    private Long entrepriseid;
    private String poste;
    private String localisation;
    private Double salaire;
    private String contrat;
    private String status;
    private LocalDate datePublication;
}
