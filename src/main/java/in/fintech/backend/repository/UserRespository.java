package in.fintech.backend.repository;

import in.fintech.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
