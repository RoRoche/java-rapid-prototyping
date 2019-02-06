package fr.guddy.test;

import com.pragmaticobjects.oo.tests.TestCase;
import com.pragmaticobjects.oo.tests.junit5.TestsSuite;
import fr.guddy.test.assertions.DeleteUserAssertion;
import fr.guddy.test.assertions.DuplicateUserAssertion;
import fr.guddy.test.assertions.InsertUserAssertion;
import org.dizitart.no2.Nitrite;

final class UserTest extends TestsSuite {
    private UserTest() {
        super(
                new TestCase(
                        "when creating an user, it's in database",
                        new InsertUserAssertion(
                                Nitrite.builder()
                                        .openOrCreate("user", "password"),
                                new User(
                                        "romain.rochegude",
                                        "Romain",
                                        "Rochegude"
                                )
                        )
                ),
            new TestCase(
                "when deleting an user, it isn't in database",
                new DeleteUserAssertion(
                    Nitrite.builder()
                        .openOrCreate("user", "password"),
                    new User(
                        "romain.rochegude",
                        "Romain",
                        "Rochegude"
                    )
                )
            ),
            new TestCase(
                "when duplicating an user, it fails",
                new DuplicateUserAssertion(
                    Nitrite.builder()
                        .openOrCreate("user", "password"),
                    new User(
                        "romain.rochegude",
                        "Romain",
                        "Rochegude"
                    )
                )
            )
        );
    }
}