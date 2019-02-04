package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import fr.guddy.test.User;
import org.assertj.core.api.Assertions;
import org.dizitart.no2.objects.ObjectRepository;

public final class AssertUserIsInDatabase implements Assertion {

  private final ObjectRepository<User> repository;
  private final User user;

  public AssertUserIsInDatabase(final ObjectRepository<User> repository, final User user) {
    this.repository = repository;
    this.user = user;
  }

  @Override
  public void check() throws Exception {
    Assertions.assertThat(
        repository.find()
            .toList()
            .stream()
            .anyMatch(user::equals)
    ).isTrue();
  }
}
