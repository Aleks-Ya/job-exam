package ru.yaal.jobexam;

import java.util.List;

/**
 * @author Aleksey Yablokov
 */
public interface Subject {
    List<? extends Question> getQuestions();
    String getName();
    String getPath();
}
