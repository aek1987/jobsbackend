package jobplatform.comptes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import DTO.AccountDTO;
import jobplatform.security.JwtService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Account account) {
        Map<String, Object> response = new HashMap<>();
        try {
            AccountDTO savedAccount = accountService.register(account);         
            
            response.put("message", "✅ Utilisateur enregistré avec succès !");
            response.put("compte", savedAccount);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Erreur interne inconnue";
            response.put("error", "❌ Erreur interne : " + msg);
            return ResponseEntity.status(500).body(response);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Account account) {
        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
            );

            Account user = (Account) authentication.getPrincipal();
            String token = jwtService.generateToken(user.getEmail());           

              
          
            response.put("message", "Connexion réussie");               
            response.put("email", user.getEmail());
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("refId", user.getRefId());
            response.put("token", token);
            

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (BadCredentialsException e) {
            response.put("error", "Identifiants invalides");
            return ResponseEntity.status(401)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            response.put("error", "Erreur interne : " + e.getMessage());
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }
}
