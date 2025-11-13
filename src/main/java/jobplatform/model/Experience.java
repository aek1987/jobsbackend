package jobplatform.model;

<<<<<<< Updated upstream
=======


package com.example.jobplatform.model;


>>>>>>> Stashed changes
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
<<<<<<< Updated upstream
public class Experience {
=======

public class Experience {

public class Offre {

>>>>>>> Stashed changes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< Updated upstream
    private String entreprise;
    private LocalDate dateDebut;
    private LocalDate dateFin;
=======

    private String poste;
    private String entreprise;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    // 🔗 Relation avec une entreprise
    private Long entrepriseId;

    // 🧾 Détails du poste
>>>>>>> Stashed changes
    private String poste;
    private String description;
}
