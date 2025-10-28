package jobplatform.comptes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    /**
     * 🟢 Endpoint pour l'inscription d'un nouvel utilisateur
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try {
            accountService.register(account);
            return ResponseEntity.ok("✅ Utilisateur enregistré avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }

    /**
     * 🟢 Endpoint pour la connexion d'un utilisateur
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {
            System.out.println("🔍 Tentative de login : " + account.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("✅ Connexion réussie : " + userDetails.getUsername());
            return ResponseEntity.ok("✅ Connexion réussie pour : " + userDetails.getUsername());

        } catch (BadCredentialsException e) {
            System.err.println("❌ Identifiants invalides");
            return ResponseEntity.status(401).body("❌ Identifiants invalides");
        } catch (Exception e) {
            e.printStackTrace(); // ← IMPORTANT pour voir l’exception complète
            return ResponseEntity.internalServerError().body("❌ Erreur interne : " + e.getMessage());
        }
    }

}
