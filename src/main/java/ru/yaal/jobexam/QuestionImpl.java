package ru.yaal.jobexam;

import java.util.List;

/**
 * @author Aleksey Yablokov
 */
public class QuestionImpl implements Question {
    private String text;
    private List<Answer> answers;

    public QuestionImpl(String text, List<Answer> answers) {
        assert text != null && !text.isEmpty();
        assert answers != null && !answers.isEmpty();
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
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
