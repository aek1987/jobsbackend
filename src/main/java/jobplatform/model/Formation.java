package jobplatform.model;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "formation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diplome;
    private String etablissement;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
}
