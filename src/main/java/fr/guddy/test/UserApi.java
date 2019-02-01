package fr.guddy.test;

import io.javalin.Javalin;

public final class UserApi implements Application {

    private final Javalin javalin;
    private final int port;

    public UserApi(final Javalin javalin, final int port) {
        this.javalin = javalin;
        this.port = port;
    }

    public UserApi(final int port) {
        this(
                Javalin.create()
                        .get("/", ctx -> ctx.result("Hello World")),
                port
        );
    }

    @Override
    public void start() {
        javalin.start(port);
    }

    @Override
    public void stop() {
        javalin.stop();
    }
}
