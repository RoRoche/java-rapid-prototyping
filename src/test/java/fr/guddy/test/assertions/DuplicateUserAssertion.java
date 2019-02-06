package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import fr.guddy.test.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public final class DuplicateUserAssertion implements Assertion {

  private final Assertion delegate;

  public DuplicateUserAssertion(final Assertion delegate) {
    this.delegate = delegate;
  }

  public DuplicateUserAssertion(final ObjectRepository<User> repository, final User user) {
    this(
        new FixturedAssertion(
            new AssertDuplicateUserFails(
                repository,
                user
            ),
            new InsertUserFixture(
                repository,
                user
            )
        )
    );
  }

  public DuplicateUserAssertion(final Nitrite db, final User user) {
    this(db.getRepository(User.class), user);
  }

  @Override
  public void check() throws Exception {
    delegate.check();
  }
}
