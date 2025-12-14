package jobplatform.model;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "candidat_competences", joinColumns = @JoinColumn(name = "candidat_ref_id"))
    @Column(name = "competence")
    private List<String> competences = new ArrayList<>();
    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "candidat_langues", joinColumns = @JoinColumn(name = "candidat_ref_id"))
    @Column(name = "langue")
    private List<String> langues = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(
        name = "candidat_favoris",
        joinColumns = @JoinColumn(name = "candidat_id")
    )
    @Column(name = "offre_id")
     private List<Long> favoris; // ids des offres favorites
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidat_ref_id") // ⚠ obligatoire pour lier les enfants à ce candidat
    private List<Experience> experiences = new ArrayList<>();
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidat_ref_id")
    private List<Formation> formations = new ArrayList<>(); 
 
    

 
}
