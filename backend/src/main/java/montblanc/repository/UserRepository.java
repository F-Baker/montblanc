package montblanc.repository;

import montblanc.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndEnabled(String email, boolean enabled);

    @Query(nativeQuery = true, value =
            "SELECT u.* FROM user u " +
                    "INNER JOIN user_role ur ON u.user_id = ur.user_id " +
                    "INNER JOIN role r ON r.role_id=ur.role_id " +
                    "INNER JOIN enrollment_status es on es.enrollment_status_id=u.enrollment_status_id " +
                    "WHERE r.name = ? AND u.enabled=1")
    List<User> findByRole(String roleName);

}
