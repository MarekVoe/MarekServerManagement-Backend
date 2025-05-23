package dev.marekvoe.marekservermanagement.repositories;

import dev.marekvoe.marekservermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
