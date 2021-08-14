package ie.cct.gtp.robs.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ie.cct.gtp.robs.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	@Query("SELECT u FROM User u WHERE u.id = ?1")
	Optional<User> findById(Long id);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Optional<User> findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.role = ?1")
	Optional<User> findByProfile(String profile);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM User u WHERE u.id = ?1")
	int deleteUserById(Long id);
	
}
