package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    @NonNull
    @Override
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"bookWishlist"}
    )
    List<User> findAll();
    List<UserProjection> findAllProjectedBy();
}
