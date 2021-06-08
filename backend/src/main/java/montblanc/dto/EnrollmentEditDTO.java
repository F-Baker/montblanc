package montblanc.dto;

public class EnrollmentEditDTO {
    private long studentId;
    private int statusId;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "EnrollmentEditDTO [studentId=" + studentId + ", statusId=" + statusId + "]";
    }
}
