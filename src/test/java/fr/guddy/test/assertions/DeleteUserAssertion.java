package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import fr.guddy.test.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public final class DeleteUserAssertion implements Assertion {

  private final Assertion delegate;

  public DeleteUserAssertion(final Assertion delegate) {
    this.delegate = delegate;
  }

  public DeleteUserAssertion(final ObjectRepository<User> repository, final User user) {
    this(
        new FixturedAssertion(
            new AssertUserIsNotInDatabase(
                repository,
                user
            ),
            new InsertUserFixture(
                repository,
                user
            ),
            new DeleteUserFixture(
                repository,
                user
            )
        )
    );
  }

  public DeleteUserAssertion(final Nitrite db, final User user) {
    this(db.getRepository(User.class), user);
  }

  @Override
  public void check() throws Exception {
    delegate.check();
  }
}
