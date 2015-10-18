package ru.yaal.jobexam.vertex;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author Aleksey Yablokov
 */
@RunWith(VertxUnitRunner.class)
@Ignore
public class WebVerticalTest2 {

    private Vertx vertx;

    @Before
    public void setUp() {
        vertx = Vertx.vertx();
        vertx.deployVerticle(WebVertical.class.getName());
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
    public void tearDown() {
        vertx.close();
    }


    @Test
    public void testMyApplication() {
        HttpClient httpClient = vertx.createHttpClient();
        HttpClientRequest request = httpClient.get(8080, "localhost", "/?path=programmer/spring.html");
        request.handler(resp -> {
            assertEquals(200, resp.statusCode());
        });
    }
}