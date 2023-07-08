package montblanc.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enrollment_status")
public class EnrollmentStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_status_id")
    private int enrollmentStatusId;
    private String name;

    public int getEnrollmentStatusId() {
        return enrollmentStatusId;
    }

    public void setEnrollmentStatusId(int enrollmentStatusId) {
        this.enrollmentStatusId = enrollmentStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
