package jobplatform.comptes;



import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import comptes.mapper.AccountMapper;

@Service
public class AccountService implements UserDetailsService {

    private final AccountMapper accountMapper; 
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountMapper accountMapper,PasswordEncoder passwordEncoder) {
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountMapper.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + email);
        }

     

        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword()) // doit être encodé (BCrypt)
                .roles(account.getRole())  // ex: ROLE_ADMIN, ROLE_CANDIDAT
                .build();
    }

    public void register(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("⚠️ Account ne doit pas être null");
        }
        if (accountMapper == null) {
            throw new IllegalStateException("⚠️ accountMapper n'est pas injecté !");
        }
        if (passwordEncoder == null) {
            throw new IllegalStateException("⚠️ passwordEncoder n'est pas injecté !");
        }

        System.out.println("🔍 Enregistrement de l'utilisateur : " + account.getEmail());

        // Encodage du mot de passe
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        // Insertion dans la base via MyBatis
        accountMapper.insert(account);

        System.out.println("✅ Utilisateur enregistré !");
    }
}
