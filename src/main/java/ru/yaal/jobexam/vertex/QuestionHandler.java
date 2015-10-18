package ru.yaal.jobexam.vertex;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import ru.yaal.jobexam.FreemarkerProcessor;
import ru.yaal.jobexam.Subject;
import ru.yaal.jobexam.SubjectsDateSource;

import java.io.IOException;

/**
 * @author Aleksey Yablokov
 */
class QuestionHandler implements Handler<RoutingContext> {
    private SubjectsDateSource subjectsDateSource;
    private FreemarkerProcessor freemarkerProcessor;

    public QuestionHandler(SubjectsDateSource subjectsDateSource, FreemarkerProcessor freemarkerProcessor) {
        this.subjectsDateSource = subjectsDateSource;
        this.freemarkerProcessor = freemarkerProcessor;
    }

    @Override
    public void handle(RoutingContext event) {
        try {
            String path = event.request().path().replaceFirst("/$", "") + ".html";
            Subject subject = subjectsDateSource.takeSubjectByPath(path);
            String html = freemarkerProcessor.process(subject);
            HttpServerResponse response = event.request().response();
            response.putHeader("content-type", "text/html");
            response.end(html);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
