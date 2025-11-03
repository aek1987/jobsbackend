package jobplatform.comptes;


	import jakarta.persistence.*;
	import lombok.*;

	
	@Entity
	@Table(name = "account")
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Account {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    private String email;
	    private String password;
	    private String username;
	    private String role;
	    private Long refId;
	}
