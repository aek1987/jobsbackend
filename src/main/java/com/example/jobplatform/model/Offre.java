package com.example.jobplatform.model;

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

    private String titre;
    private String entreprise;
    private String localisation;
    private Double salaire;

    @Column(length = 2000)
    private String description;
}
