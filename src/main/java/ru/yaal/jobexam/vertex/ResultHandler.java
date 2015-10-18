package ru.yaal.jobexam.vertex;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * @author Aleksey Yablokov
 */
class ResultHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext event) {
        System.out.println("RESULT!!!!!!!!!!!!!!!!!!!!!!!!!");
        event.response().end("Your result saved");
    }
}
