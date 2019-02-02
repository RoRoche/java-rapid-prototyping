package fr.guddy.test;

import org.dizitart.no2.objects.ObjectRepository;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public final class UserApiExtension implements BeforeEachCallback, AfterEachCallback {

    private final Application application;

    public UserApiExtension(final int port, final ObjectRepository<User> userRepository) {
        this.application = new UserApi(port, userRepository);
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
