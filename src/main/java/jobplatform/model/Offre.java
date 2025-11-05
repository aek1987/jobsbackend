package jobplatform.model;




import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "offre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "\"entrepriseId\"")
    private Long entrepriseId;
    private String poste;
    private String description;
    private String localisation;
    private Double salaire;
    private String contrat;
    private String teletravail;
    private String niveauExperience;
    private String avantages;
    private String status;

    private LocalDate datePublication;
    private LocalDate dateLimite;

    @ElementCollection
    private List<String> missions;

    @ElementCollection
    private List<String> competences;

    @ElementCollection
    private List<String> langues;
}
