package fr.guddy.test;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public final class UserApiExtension implements BeforeEachCallback, AfterEachCallback {

    private final Application application /*= new UserApi(7000)*/;

    public UserApiExtension(final int port) {
        application = new UserApi(port);
    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        application.start();
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        application.stop();
    }
}
