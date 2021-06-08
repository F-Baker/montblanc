package montblanc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import montblanc.Entity.EnrollmentStatus;

public interface EnrollmentStatusRepository extends JpaRepository<EnrollmentStatus, Integer> {

	EnrollmentStatus findByName(String string);
	@Query(value = "select * from enrollment_status", nativeQuery = true)
	List<EnrollmentStatus> findAll();

}
