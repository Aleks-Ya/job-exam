package ru.yaal.jobexam.vertex;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * @author Aleksey Yablokov
 */
class ResultHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext context) {
        System.out.println("result: " + context.request().formAttributes().toString());
        context.response().end("Your result saved \n" + context.request().formAttributes().toString());
    }
}
