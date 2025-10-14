package jobplatform.model;


	import jakarta.persistence.*;
	import lombok.*;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@Entity
	@Table(name = "account")
	public class Account {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String email;
	    private String password;
	    private String username;

	    // rôle : candidat, entreprise ou admin
	    private String role;

	    // référence à l'id de l'entité liée (candidat ou entreprise)
	    private Long refId;
	}
