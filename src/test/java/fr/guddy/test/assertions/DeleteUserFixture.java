package fr.guddy.test.assertions;

import fr.guddy.test.User;
import org.dizitart.no2.objects.ObjectRepository;

public final class DeleteUserFixture implements Fixture {
    private final ObjectRepository<User> repository;
    private final User user;

    public DeleteUserFixture(final ObjectRepository<User> repository, final User user) {
        this.repository = repository;
        this.user = user;
    }

    @Override
    public void run() {
        repository.remove(user);
    }
}
