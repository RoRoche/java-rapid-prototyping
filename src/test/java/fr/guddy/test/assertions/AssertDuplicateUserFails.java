package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import fr.guddy.test.User;
import org.assertj.core.api.Assertions;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;

public final class AssertDuplicateUserFails implements Assertion {

  private final ObjectRepository<User> repository;
  private final User user;

  public AssertDuplicateUserFails(final ObjectRepository<User> repository, final User user) {
    this.repository = repository;
    this.user = user;
  }

  @Override
  public void check() throws Exception {
    Assertions.assertThatThrownBy(
        () -> repository.insert(user)
    ).isInstanceOf(
        UniqueConstraintException.class
    ).withFailMessage(
        "NO2.10003: unique key constraint violation for userId"
    );
  }
}
