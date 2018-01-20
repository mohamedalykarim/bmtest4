package bmtestv4.android.mohalim.bmtestv4;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Mohamed ALi on 1/6/2018.
 */

public class CurrentSession implements Parcelable {
    private String questionText;
    private int correctAnswer, isAnswered, isCorrect, chosenAnswer, sessionId, questionId;
    private String[] choices;

    public CurrentSession(String questionText, int correctAnswer, int isAnswered, int isCorrect, int chosenAnswer, int sessionId, int questionId, String[] choices) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.isAnswered = isAnswered;
        this.isCorrect = isCorrect;
        this.chosenAnswer = chosenAnswer;
        this.sessionId = sessionId;
        this.questionId = questionId;
        this.choices = choices;
    }

    protected CurrentSession(Parcel in) {
        questionText = in.readString();
        correctAnswer = in.readInt();
        isAnswered = in.readInt();
        isCorrect = in.readInt();
        chosenAnswer = in.readInt();
        sessionId = in.readInt();
        questionId = in.readInt();
        choices = in.createStringArray();
    }

    public static final Creator<CurrentSession> CREATOR = new Creator<CurrentSession>() {
        @Override
        public CurrentSession createFromParcel(Parcel in) {
            return new CurrentSession(in);
        }

        @Override
        public CurrentSession[] newArray(int size) {
            return new CurrentSession[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(questionText);
        parcel.writeInt(correctAnswer);
        parcel.writeInt(isAnswered);
        parcel.writeInt(isCorrect);
        parcel.writeInt(chosenAnswer);
        parcel.writeInt(sessionId);
        parcel.writeInt(questionId);
        parcel.writeStringArray(choices);
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(int isAnswered) {
        this.isAnswered = isAnswered;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(int chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }
}
