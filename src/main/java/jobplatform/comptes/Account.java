package jobplatform.comptes;


	import jakarta.persistence.*;
	import lombok.*;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Account {
	    private Long id;
	    private String email;
	    private String password;
	    private String username;
	    private String role;
	    private Long refId;
	}
