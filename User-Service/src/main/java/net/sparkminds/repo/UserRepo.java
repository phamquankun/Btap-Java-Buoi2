package net.sparkminds.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.sparkminds.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);
	
}
