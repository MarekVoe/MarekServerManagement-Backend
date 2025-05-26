package dev.marekvoe.marekservermanagement.repositories;


import dev.marekvoe.marekservermanagement.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    boolean existsByName(String name);
}
