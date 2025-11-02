package jobplatform.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jobplatform.comptes.AccountService;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Configuration de l'AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       AccountService accountService,
                                                       PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(accountService).passwordEncoder(passwordEncoder);
        return builder.build();
    }

    // ✅ Configuration principale de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // désactive CSRF pour les API REST
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 🔥 Active la config CORS ci-dessous
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // endpoints publics
                .anyRequest().authenticated() // le reste est protégé
            )
            .formLogin(login -> login.permitAll()); // optionnel, utile si tu veux tester via navigateur
        return http.build();
    }

    // ✅ Configuration CORS — c’est ce qui résout ton problème
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 🌍 Autorise ton front local et ton front déployé (si applicable)
        configuration.setAllowedOrigins(List.of(
                "http://localhost:4200",           // ton front Angular local
                "https://jobsfrontend.onrender.com" // <-- remplace si ton front Render a une autre URL
        ));

        // 🔧 Méthodes et en-têtes autorisés
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
