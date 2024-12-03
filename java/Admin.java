package java;

import java.util.List;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    public void addQuestion(List<QuizQuestion> questionList, QuizQuestion newQuestion) {
        questionList.add(newQuestion);
    }

    public void deleteQuestion(List<QuizQuestion> questionList, int questionId) {
        questionList.removeIf(q -> q.getId() == questionId);
    }
}
