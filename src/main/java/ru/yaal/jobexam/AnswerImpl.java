package ru.yaal.jobexam;

/**
 * @author Aleksey Yablokov
 */
public class AnswerImpl implements Answer {
    private String text;
    private boolean isRight;

    public AnswerImpl(String text, boolean isRight) {
        assert text != null && !text.isEmpty();
        this.text = text;
        this.isRight = isRight;
    }

    public String getText() {
        return text;
    }

    public boolean isRight() {
        return isRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerImpl answer = (AnswerImpl) o;
        //noinspection SimplifiableIfStatement
        if (isRight != answer.isRight) return false;
        return text.equals(answer.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
