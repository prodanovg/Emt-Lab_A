package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByUserUsername(String username);
}
