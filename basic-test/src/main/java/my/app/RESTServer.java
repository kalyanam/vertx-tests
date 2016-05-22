package my.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

/**
 * Created by mkalyan on 5/22/16.
 */
public class RESTServer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(RESTServer.class);

    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(getVertx());
        router.route().handler(CorsHandler.create("*"));
        router.get().path("/products").handler(this::longRunningOp);

        getVertx().createHttpServer().requestHandler(router::accept).listen(8081);
        fut.complete();
    }

    private void longRunningOp(RoutingContext routingContext) {
        logger.info("Starting to run the op...");
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
            routingContext.fail(500);
        }
        logger.info("Done and sending the response...");
        routingContext.response().end("Many many products...");
    }
}
