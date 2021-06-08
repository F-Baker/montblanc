package montblanc.repository;

import montblanc.Entity.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentStatusRepository extends JpaRepository<EnrollmentStatus, Integer> {

    EnrollmentStatus findByName(String string);

    @Query(value = "SELECT * FROM enrollment_status", nativeQuery = true)
    List<EnrollmentStatus> findAll();

}
