package fr.guddy.test;

import static io.javalin.apibuilder.ApiBuilder.crud;

import io.javalin.Javalin;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public final class UserApi implements Application {

  private final Javalin javalin;
  private final int port;

  public UserApi(final Javalin javalin, final int port) {
    this.javalin = javalin;
    this.port = port;
  }

  public UserApi(final int port, final Nitrite db) {
    this(port, db.getRepository(User.class));
  }

  public UserApi(final int port, final ObjectRepository<User> userRepository) {
    this(
        Javalin.create()
            .get("/", ctx -> ctx.result("Hello World"))
            .routes(() ->
                crud(
                    "users/:user-id",
                    new Users(userRepository)
                )
            ),
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
