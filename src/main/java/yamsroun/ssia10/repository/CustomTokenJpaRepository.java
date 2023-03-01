package yamsroun.ssia10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yamsroun.ssia10.domain.CustomToken;

import java.util.Optional;

public interface CustomTokenJpaRepository extends JpaRepository<CustomToken, Integer> {

    Optional<CustomToken> findTokenByIdentifier(String identifier);
}
