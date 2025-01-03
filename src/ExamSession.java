package java;

import java.util.List;

public class ExamSession {
    private Student student;
    private List<QuizQuestion> questions;
    private long startTime;
    private long endTime;
    private long timeSpent;

    public ExamSession(Student student, List<QuizQuestion> questions) {
        this.student = student;
        this.questions = questions;
    }

    public void startExam() {
        this.startTime = System.currentTimeMillis();
    }

    public void endExam() {
        this.endTime = System.currentTimeMillis();
        this.timeSpent = endTime - startTime;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }
}