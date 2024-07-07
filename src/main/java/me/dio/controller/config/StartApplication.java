package me.dio.controller.config;

import me.dio.controller.config.secutiry.PasswordEncoderConfig;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartApplication implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordEncoderConfig passwordEncoder;

    public StartApplication(UserRepository repository, PasswordEncoderConfig passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("user");
        if (user == null) {
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            String encryptedPassword = passwordEncoder.passwordEncoder().encode("user123");
            user.setPassword(encryptedPassword);
            user.getRoles().add("USERS");
            repository.save(user);
        }
    }

}
