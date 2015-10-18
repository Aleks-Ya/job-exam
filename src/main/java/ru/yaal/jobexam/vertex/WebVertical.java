package ru.yaal.jobexam.vertex;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import ru.yaal.jobexam.FreemarkerProcessor;
import ru.yaal.jobexam.FreemarkerProcessorImpl;
import ru.yaal.jobexam.HtmlSubjectDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    public void start() throws URISyntaxException, IOException {
        URL spring = getClass().getClassLoader().getResource("subjects/programmer/spring.html");
        assert spring != null;

        File baseDir = new File(spring.toURI()).getParentFile().getParentFile();
        HtmlSubjectDataSource subjects = new HtmlSubjectDataSource(baseDir);

        FreemarkerProcessor processor = new FreemarkerProcessorImpl();

        Router router = Router.router(vertx);
        router.route("/result").handler(new ResultHandler());
        router.routeWithRegex("^((?!/result).)*$").handler(new QuestionHandler(subjects, processor));

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept);
        server.listen(8080);
    }
}
