package jobplatform.comptes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * 🟢 Endpoint pour l'inscription d'un nouvel utilisateur
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try {
            accountService.register(account);
            return ResponseEntity.ok(Map.of("message", "✅ Utilisateur enregistré avec succès !"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "❌ Erreur lors de l'enregistrement : " + e.getMessage()));
        }
    }

    /**
     * 🟢 Endpoint pour la connexion d'un utilisateur
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {
            System.out.println("🔍 Tentative de login : " + account.getEmail());

            // 🔐 Authentification
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("✅ Connexion réussie : " + userDetails.getUsername());

            // 🔑 Génération du token JWT
            String token = jwtService.generateToken(userDetails.getUsername());
            // 🧩 Extraction du rôle simplifié
            String fullRole = userDetails.getAuthorities().iterator().next().getAuthority();
            String simpleRole = fullRole.replace("ROLE_", "").toLowerCase();

            // 🧱 Construction de la réponse JSON
            Map<String, Object> response = new HashMap<>();
            response.put("message", "✅ Connexion réussie");
            response.put("user", userDetails.getUsername());
            response.put("roles", simpleRole);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            System.err.println("❌ Identifiants invalides");
            return ResponseEntity
                    .status(401)
                    .body(Map.of("error", "❌ Identifiants invalides"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("error", "❌ Erreur interne : " + e.getMessage()));
        }
    }
}
