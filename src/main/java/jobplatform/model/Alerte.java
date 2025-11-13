package jobplatform.model;



import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Alerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String motCle;
    private String lieu;

    @ElementCollection
    private List<String> contrats;

    @ElementCollection
    private List<String> secteurs;

    @ElementCollection
    private List<String> teletravail;

    private String frequence;
    private boolean active;
    private LocalDate dateCreation;
    private String email;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMotCle() { return motCle; }
    public void setMotCle(String motCle) { this.motCle = motCle; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public List<String> getContrats() { return contrats; }
    public void setContrats(List<String> contrats) { this.contrats = contrats; }

    public List<String> getSecteurs() { return secteurs; }
    public void setSecteurs(List<String> secteurs) { this.secteurs = secteurs; }

    public List<String> getTeletravail() { return teletravail; }
    public void setTeletravail(List<String> teletravail) { this.teletravail = teletravail; }

    public String getFrequence() { return frequence; }
    public void setFrequence(String frequence) { this.frequence = frequence; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
