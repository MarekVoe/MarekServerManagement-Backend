package dev.marekvoe.marekservermanagement.services;

import dev.marekvoe.marekservermanagement.models.User;
import dev.marekvoe.marekservermanagement.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void createAdmin(String username, String rawPassword, String email) {
        if (userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        User admin = new User();
        admin.setUsername(username);
        admin.setPasswordHash(encoder.encode(rawPassword));
        admin.setEmail(email);
        admin.setRole("ROLE_ADMIN");
        userRepo.save(admin);
    }
}
