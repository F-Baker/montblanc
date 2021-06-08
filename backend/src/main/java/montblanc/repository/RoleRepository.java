package montblanc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import montblanc.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
