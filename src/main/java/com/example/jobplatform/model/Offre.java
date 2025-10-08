package com.example.jobplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

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

    // 🔗 Relation avec une entreprise
    private Long entrepriseId;

    // 🧾 Détails du poste
    private String poste;

    @Column(length = 2000)
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
