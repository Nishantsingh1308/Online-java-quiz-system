package java;

public class Student extends User {
    private String studentId;

    public Student(String username, String password, String studentId) {
        super(username, password);
        this.studentId = studentId;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
