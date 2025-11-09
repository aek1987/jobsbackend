package jobplatform.comptes;

import comptes.mapper.AccountMapper;
import comptes.mapper.CandidatMapper;
import comptes.mapper.EntrepriseMapper;
import jobplatform.model.Candidat;
import jobplatform.model.Entreprise;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements UserDetailsService {

    private final AccountMapper accountMapper;
    private final CandidatMapper candidatMapper;
    private final EntrepriseMapper entrepriseMapper;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountMapper accountMapper,
                          CandidatMapper candidatMapper,
                          EntrepriseMapper entrepriseMapper,
                          PasswordEncoder passwordEncoder) {
        this.accountMapper = accountMapper;
        this.candidatMapper = candidatMapper;
        this.entrepriseMapper = entrepriseMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountMapper.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + email);
        }

        return account; 
    }

    /**
     * Inscription d’un nouveau compte
     * Si rôle = candidat => crée un candidat vide
     * Si rôle = entreprise => crée une entreprise vide
     */
    @Transactional
    public void register(Account account) {
        if (account == null || account.getEmail() == null || account.getPassword() == null) {
            throw new IllegalArgumentException(" Email et mot de passe sont obligatoires.");
        }

        // Vérifier si l’email existe déjà
        if (accountMapper.findByEmail(account.getEmail()) != null) {
            throw new IllegalStateException(" Cet email est déjà utilisé : " + account.getEmail());
        }

        // Encodage du mot de passe
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        // Déterminer le rôle
        String role = account.getRole() == null ? "candidat" : account.getRole().toLowerCase();

        Long refId = null;
        switch (role) {
            case "candidat":
                Candidat candidat = Candidat.builder()
                        .email(account.getEmail())
                        .username(account.getUsername())
                        .status("incomplet")
                        .build();
                candidatMapper.insert(candidat);
                refId = candidat.getRefId();
                break;

            case "entreprise":
                Entreprise entreprise = Entreprise.builder()
                        .email(account.getEmail())
                        .username(account.getUsername())
                        .status("incomplet")
                        .build();
                entrepriseMapper.insert(entreprise);
                refId = entreprise.getId();
                break;

            default:
                throw new IllegalArgumentException("⚠ Rôle non reconnu : " + role);
        }

        // Enregistrer le compte
        account.setRole(role);
        account.setRefId(refId);
        accountMapper.insert(account);

        System.out.println("✅ Compte " + role + " créé : " + account.getEmail());
    }
}
