package ru.yaal.jobexam.vertex;

import freemarker.template.TemplateException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import ru.yaal.jobexam.FreemarkerProcessor;
import ru.yaal.jobexam.FreemarkerProcessorImpl;
import ru.yaal.jobexam.HtmlSubjectDataSource;
import ru.yaal.jobexam.Subject;
import ru.yaal.jobexam.SubjectsDateSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Aleksey Yablokov
 */
public class WebVertical extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(WebVertical.class.getName());
    }

    @Override
    public void start() throws Exception {
        URL spring = getClass().getClassLoader().getResource("subjects/programmer/spring.html");
        assert spring != null;
        File baseDir = new File(spring.toURI()).getParentFile().getParentFile();
        HtmlSubjectDataSource subjects = new HtmlSubjectDataSource(baseDir);

        FreemarkerProcessor processor = new FreemarkerProcessorImpl();

        WebHandler handler = new WebHandler(subjects, processor);

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(handler);
        server.listen(8080);
    }

    class WebHandler implements Handler<HttpServerRequest> {
        private SubjectsDateSource subjectsDateSource;
        private FreemarkerProcessor freemarkerProcessor;

        public WebHandler(SubjectsDateSource subjectsDateSource, FreemarkerProcessor freemarkerProcessor) {
            this.subjectsDateSource = subjectsDateSource;
            this.freemarkerProcessor = freemarkerProcessor;
        }

        @Override
        public void handle(HttpServerRequest request) {
            try {
                String path = request.path().replaceFirst("/$", "") + ".html";
                Subject subject = subjectsDateSource.takeSubjectByPath(path);
                String html = freemarkerProcessor.process(subject);
                HttpServerResponse response = request.response();
                response.putHeader("content-type", "text/html");
                response.end(html);
            } catch (IOException | TemplateException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}
