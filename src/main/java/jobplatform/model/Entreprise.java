/**
 * 
 */
package jobplatform.model;



import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "entreprise")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String phone;
    private String secteur;

    private String description;
    private String site;
    private String logo;

    private String status; // "active" ou "desactive"
}
