package ru.yaal.jobexam.vertex;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aleksey Yablokov
 */
@RunWith(VertxUnitRunner.class)
@Ignore
public class WebVerticalTest {

    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(WebVertical.class.getName(), context.asyncAssertSuccess());
    }

//    @Before
//    public void setUp2(TestContext context) throws InterruptedException {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                vertx = Vertx.vertx();
//                vertx.deployVerticle(WebVertical.class.getName(), context.asyncAssertSuccess());
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
//        Thread.sleep(100000);
//    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }


    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();
        HttpClient httpClient = vertx.createHttpClient();
        HttpClientRequest request = httpClient.get(8080, "localhost", "/?path=programmer/spring.html");
        request.handler(resp -> {
            context.assertEquals(200, resp.statusCode());
            async.complete();
        });

    }
}