package ru.yaal.jobexam;

import java.util.List;

/**
 * @author Aleksey Yablokov
 */
public class QuestionImpl implements Question {
    private String text;
    private List<? extends Answer> answers;

    public QuestionImpl(String text, List<? extends Answer> answers) {
        assert text != null && !text.isEmpty();
        assert answers != null && !answers.isEmpty();
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public List<? extends Answer> getAnswers() {
        return answers;
    }

    @Override
    public int rightAnswersCount() {
        return (int) answers.stream().filter(Answer::isRight).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionImpl question = (QuestionImpl) o;
        //noinspection SimplifiableIfStatement
        if (!text.equals(question.text)) return false;
        return answers.equals(question.answers);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
