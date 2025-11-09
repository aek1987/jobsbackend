package jobplatform.model;



import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "candidat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refId;

    private String username;
    private String email;
    private String fonction;
    private String phone;
    private String bio;
    private String cv;
    private String adresse;

    private String status; // active, desactive, en_attente_validation, incomplet, pret

    private Integer progression;

    @ElementCollection
    @CollectionTable(
        name = "candidat_favoris",
        joinColumns = @JoinColumn(name = "candidat_id")
    )
    @Column(name = "offre_id")
     private List<Long> favoris; // ids des offres favorites

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Formation> formations;

    @ElementCollection
    private List<String> competences;

    @ElementCollection
    private List<String> langues;
}
