package jobplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidature")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 juste l'ID de l'offre, pas l'objet complet
    @Column(name = "offre_id", nullable = false)
    private Long offreId;

    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    private Candidat candidat;

    private LocalDateTime dateCandidature;

    private String statut; // ex: "en attente", "acceptée", "refusée"

    @Column(length = 2000)
    private String message;
}
