package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import fr.guddy.test.User;
import fr.guddy.test.assertions.fixtures.ChainedFixtures;
import fr.guddy.test.assertions.fixtures.DeleteUserFixture;
import fr.guddy.test.assertions.fixtures.FixturedAssertion;
import fr.guddy.test.assertions.fixtures.InsertUserFixture;
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
            new ChainedFixtures(
                new InsertUserFixture(
                    repository,
                    user
                ),
                new DeleteUserFixture(
                    repository,
                    user
                )
            ),
            new AssertUserIsNotInDatabase(
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
