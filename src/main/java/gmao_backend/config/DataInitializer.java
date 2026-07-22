package gmao_backend.config;

import gmao_backend.entity.Role;
import gmao_backend.entity.User;
import gmao_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@gmao.com")) {
            User admin = User.builder()
                    .firstname("System")
                    .lastName("Admin")
                    .email("admin@gmao.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.MANAGER)
                    .build();

            userRepository.save(admin);
            log.info("Compte manager par défaut créé : admin@gmao.com / admin123");
        }
    }

}
