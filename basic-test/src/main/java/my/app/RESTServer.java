package my.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

/**
 * Created by mkalyan on 5/22/16.
 */
public class RESTServer extends AbstractVerticle {
    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(getVertx());
        router.route().handler(CorsHandler.create("*"));
        router.get().path("/products").handler(this::longRunningOp);

        getVertx().createHttpServer().requestHandler(router::accept).listen(8081);
        fut.complete();
    }

    private void longRunningOp(RoutingContext routingContext) {
        routingContext.response().end("All is well!");
    }
}
