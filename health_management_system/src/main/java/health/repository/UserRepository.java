package health.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import health.beans.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 Optional<User> findByUsername(String username);
}
