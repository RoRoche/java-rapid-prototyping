package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;
import org.assertj.core.util.Lists;

import java.util.List;

public final class FixturedAssertion implements Assertion {

    private final List<Fixture> fixture;
    private final Assertion assertion;

    public FixturedAssertion(final List<Fixture> fixture, final Assertion assertion) {
        this.fixture = fixture;
        this.assertion = assertion;
    }

    public FixturedAssertion(final Fixture fixture, final Assertion assertion) {
        this(
                Lists.newArrayList(fixture),
                assertion
        );
    }

    @Override
    public void check() throws Exception {
        fixture.forEach(Fixture::run);
        assertion.check();
    }
}
