package in.fintech.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF ko disable kar rahe hain kyunki hum H2 Console aur REST APIs use karenge
            .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api/finance/add").hasRole("ADMIN")
                .requestMatchers("/api/finance/summary").hasAnyRole("ADMIN", "ANALYST")
                .requestMatchers("/api/finance/all").hasAnyRole("VIEWER", "ANALYST", "ADMIN")
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .headers(headers -> headers.frameOptions(f->f.disable()));
                 // H2 Console ke liye access allow karna

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("abdullah")
                .password("abdullah123")
                .roles("ADMIN")
                .build();
        UserDetails viewer = User.withDefaultPasswordEncoder()
                .username("guest")
                .password("guest123")
                .roles("VIEWER")
                .build();
        UserDetails analyst = User.withDefaultPasswordEncoder()
                .username("analyst")
                .password("analyst123")
                .roles("ANALYST")
                .build();
                return new InMemoryUserDetailsManager(admin, viewer, analyst);

    }

}