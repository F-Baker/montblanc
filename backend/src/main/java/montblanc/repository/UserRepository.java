package montblanc.repository;

import montblanc.Entity.Role;
import montblanc.Entity.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
    User findByEmailAndEnabled(String email, boolean enabled);
    
    @Query(nativeQuery = true, value = "select u.* from user u inner join user_role ur on u.user_id = ur.user_id inner join role r on r.role_id=ur.role_id inner join enrollment_status es on es.enrollment_status_id=u.enrollment_status_id where r.name = ? and u.enabled=1")
	List<User> findByRole(String roleName);
    
}
