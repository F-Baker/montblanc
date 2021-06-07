package montblanc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import montblanc.Entity.EnrollmentStatus;

public interface EnrollmentStatusRepository extends JpaRepository<EnrollmentStatus, Integer> {

	EnrollmentStatus findByName(String string);

}
