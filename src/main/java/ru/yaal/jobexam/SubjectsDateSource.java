package ru.yaal.jobexam;

import java.io.IOException;

/**
 * @author Aleksey Yablokov
 */
public interface SubjectsDateSource {
    Subject takeSubjectByPath(String path) throws IOException;
}
