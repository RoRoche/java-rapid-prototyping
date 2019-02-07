package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import fr.guddy.test.User;
import fr.guddy.test.assertions.fixtures.FixturedAssertion;
import fr.guddy.test.assertions.fixtures.InsertUserFixture;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public final class InsertUserAssertion implements Assertion {

  private final Assertion delegate;

  public InsertUserAssertion(final Assertion delegate) {
    this.delegate = delegate;
  }

  public InsertUserAssertion(final ObjectRepository<User> repository, final User user) {
    this(
        new FixturedAssertion(
            new InsertUserFixture(
                repository,
                user
            ),
            new AssertUserIsInDatabase(
                repository,
                user
            )
        )
    );
  }

  public InsertUserAssertion(final Nitrite db, final User user) {
    this(db.getRepository(User.class), user);
  }

  @Override
  public void check() throws Exception {
    delegate.check();
  }
}
