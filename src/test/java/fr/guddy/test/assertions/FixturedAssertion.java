package fr.guddy.test.assertions;

import com.pragmaticobjects.oo.tests.Assertion;

import java.util.Arrays;
import java.util.List;

public final class FixturedAssertion implements Assertion {

    private final List<Fixture> fixture;
    private final Assertion assertion;

    public FixturedAssertion(final Assertion assertion, final Fixture... fixtures) {
        this.fixture = Arrays.asList(fixtures);
        this.assertion = assertion;
    }

    @Override
    public void check() throws Exception {
        fixture.forEach(Fixture::run);
        assertion.check();
    }
}
