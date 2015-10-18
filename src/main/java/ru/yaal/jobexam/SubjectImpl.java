package ru.yaal.jobexam;

import java.util.List;

/**
 * @author Aleksey Yablokov
 */
public class SubjectImpl implements Subject {
    private List<Question> questions;
    private String name;
    private String path;

    public SubjectImpl(List<Question> questions, String name, String path) {
        this.questions = questions;
        this.name = name;
        this.path = path;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
