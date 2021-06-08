package montblanc.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "enrollment_status")
public class EnrollmentStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_status_id")
    private int enrollmentStatusId;
    private String name;
    
//    @OneToOne( mappedBy = "enrollmentStatus" )
//    private User user;

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

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
}
