package ru.yaal.jobexam;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author Aleksey Yablokov
 */
public interface FreemarkerProcessor {
    String process(Subject subject) throws IOException, TemplateException;
}
