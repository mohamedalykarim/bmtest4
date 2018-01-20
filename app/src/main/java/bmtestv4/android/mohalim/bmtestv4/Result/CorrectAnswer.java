package bmtestv4.android.mohalim.bmtestv4.Result;

/**
 * Created by Mohamed ALi on 1/20/2018.
 */

public class CorrectAnswer {
    String question, correctAnswer, choices;

    public CorrectAnswer(String question, String correctAnswer, String choices) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.choices = choices;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
