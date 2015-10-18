package ru.yaal.jobexam;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author Aleksey Yablokov
 */
public class FreemarkerProcessor {
    private Template template;

    public FreemarkerProcessor() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(FreemarkerProcessor.class, "templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        template = cfg.getTemplate("subject.ftl");
    }

    public String process(Subject subject) throws IOException, TemplateException {
        assert subject != null;
        assert subject.getName() != null && !subject.getName().isEmpty();
        assert subject.getPath() != null && !subject.getPath().isEmpty();
        assert subject.getQuestions() != null && !subject.getQuestions().isEmpty();
        StringWriter out = new StringWriter();
        template.process(subject, out);
        return out.toString();
    }
}
