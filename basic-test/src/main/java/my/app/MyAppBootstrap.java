package my.app;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mkalyan on 5/22/16.
 */
public class MyAppBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(MyAppBootstrap.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        DeploymentOptions webappOptions = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(WebappServer.class.getName(),webappOptions, res -> {
            logger.info("Deployed webapp server....");
        });

        DeploymentOptions restOptions = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(RESTServer.class.getName(), restOptions, res -> {
            logger.info("Deployed rest server...");
        });
    }
}
