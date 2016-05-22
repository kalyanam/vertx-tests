package my.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Created by mkalyan on 5/22/16.
 */
public class WebappServer extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        String webroot = "./basic-test/src/main/resources/webapp";

        Router router = Router.router(getVertx());
        router.route().handler(StaticHandler.create()
                                    .setWebRoot(webroot)
                                    .setCachingEnabled(false));

        getVertx().createHttpServer().requestHandler(router::accept).listen(8080);

        fut.complete();
    }
}
