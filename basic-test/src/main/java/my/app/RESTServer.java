package my.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mkalyan on 5/22/16.
 */
public class RESTServer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(RESTServer.class);

    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(getVertx());
        router.route().handler(CorsHandler.create("*"));
        router.get().path("/products").handler(this::executeBlockingUnordered);

        getVertx().createHttpServer().requestHandler(router::accept).listen(8081);
        fut.complete();
    }

    private void executeBlockingUnordered(RoutingContext context) {
        getVertx().executeBlocking(blockingCodeHandler -> {
            longRunningOp(context);
        }, false, resultHandler -> {
            logger.info("Succeeded....");
            resultHandler.succeeded();
        });
    }

    private void executeBlockingOrdered(RoutingContext context) {
        getVertx().executeBlocking(blockingCodeHandler -> {
            longRunningOp(context);
        }, resultHandler -> {
            logger.info("Succeeded....");
            resultHandler.succeeded();
        });
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
