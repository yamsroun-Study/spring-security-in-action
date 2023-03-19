package yamsroun.ssiach6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yamsroun.ssiach6.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
