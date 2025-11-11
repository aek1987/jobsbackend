<<<<<<< HEAD:src/main/java/jobplatform/model/Experience.java
package jobplatform.model;


=======
package com.example.jobplatform.model;
>>>>>>> d0a19bbcd85cde44dff4f6062795d2d62c8131f7:src/main/java/com/example/jobplatform/model/Offre.java

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;

@Entity
@Table(name = "experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
<<<<<<< HEAD:src/main/java/jobplatform/model/Experience.java
public class Experience {
=======
public class Offre {
>>>>>>> d0a19bbcd85cde44dff4f6062795d2d62c8131f7:src/main/java/com/example/jobplatform/model/Offre.java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD:src/main/java/jobplatform/model/Experience.java
    private String poste;
    private String entreprise;
    private LocalDate dateDebut;
    private LocalDate dateFin;
=======
    // 🔗 Relation avec une entreprise
    private Long entrepriseId;

    // 🧾 Détails du poste
    private String poste;

    @Column(length = 2000)
>>>>>>> d0a19bbcd85cde44dff4f6062795d2d62c8131f7:src/main/java/com/example/jobplatform/model/Offre.java
    private String description;

    // 🧰 Liste des missions principales
    @ElementCollection
    @CollectionTable(name = "offre_missions", joinColumns = @JoinColumn(name = "offre_id"))
    @Column(name = "mission")
    private List<String> missions;

    // 📍 Localisation du poste
    private String localisation;

    // 💰 Salaire proposé
    private Double salaire;

    // 📃 Type de contrat : CDI, CDD, Stage...
    private String contrat;

    // 📅 Dates importantes
    private LocalDate datePublication;
    private LocalDate dateLimite;

    // ✅ Statut du processus de recrutement
    @Enumerated(EnumType.STRING)
    private Statut status;

    // 🧠 Niveau d’expérience demandé
    @Enumerated(EnumType.STRING)
    private NiveauExperience niveauExperience;

    // 💡 Compétences requises
    @ElementCollection
    @CollectionTable(name = "offre_competences", joinColumns = @JoinColumn(name = "offre_id"))
    @Column(name = "competence")
    private List<String> competences;

    // 🌍 Langues demandées
    @ElementCollection
    @CollectionTable(name = "offre_langues", joinColumns = @JoinColumn(name = "offre_id"))
    @Column(name = "langue")
    private List<String> langues;

    // 🎁 Avantages proposés
    @Column(length = 1000)
    private String avantages;

    // 💻 Télétravail (Oui, Partiel, Non)
    private String teletravail;

    // ⭐ Marquer comme favori  gg
    private Boolean favori;

    // 📊 Nombre de candidatures
    private Integer candidaturesCount;

    // Enums pour des champs à choix fixes
    public enum Statut {
        POSTULE, EN_COURS, ACCEPTE, REFUSE
    }

    public enum NiveauExperience {
        JUNIOR, INTERMEDIAIRE, SENIOR, LEAD
    }
}
