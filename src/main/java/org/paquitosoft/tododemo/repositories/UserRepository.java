package org.paquitosoft.tododemo.repositories;

import java.util.Optional;

import org.paquitosoft.tododemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findUserByEmailAndPassword(String email, String password);

}
