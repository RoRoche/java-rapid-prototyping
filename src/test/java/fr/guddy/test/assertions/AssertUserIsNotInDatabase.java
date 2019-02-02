package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import fr.guddy.test.User;
import org.assertj.core.api.Assertions;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.stream.StreamSupport;

public final class AssertUserIsNotInDatabase implements Assertion {
    private final ObjectRepository<User> repository;
    private final User user;

    public AssertUserIsNotInDatabase(final ObjectRepository<User> repository, final User user) {
        this.repository = repository;
        this.user = user;
    }

    @Override
    public void check() throws Exception {
        Assertions.assertThat(
                StreamSupport.stream(repository.find().spliterator(), false)
                        .anyMatch(user::equals)
        ).isFalse();
    }
}
