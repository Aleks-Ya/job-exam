package ru.yaal.jobexam;

import java.util.List;

/**
 * @author Aleksey Yablokov
 */
public interface Question {
    String getText();
    List<? extends Answer> getAnswers();
    int rightAnswersCount();
}
