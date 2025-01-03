package java;

import java.util.List;

public class QuizResult {
    private Student student;
    private List<QuizQuestion> questionsAnswered;
    private int correctAnswers;
    private int totalQuestions;
    private long timeTaken;

    public QuizResult(Student student, List<QuizQuestion> questionsAnswered, int correctAnswers, int totalQuestions, long timeTaken) {
        this.student = student;
        this.questionsAnswered = questionsAnswered;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.timeTaken = timeTaken;
    }

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<QuizQuestion> getQuestionsAnswered() {
        return questionsAnswered;
    }

    public void setQuestionsAnswered(List<QuizQuestion> questionsAnswered) {
        this.questionsAnswered = questionsAnswered;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }
}