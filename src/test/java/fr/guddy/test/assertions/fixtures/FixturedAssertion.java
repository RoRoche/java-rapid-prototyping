package fr.guddy.test.assertions.fixtures;

import com.pragmaticobjects.oo.tests.Assertion;

public final class FixturedAssertion implements Assertion {

  private final Fixture fixture;
  private final Assertion assertion;

  public FixturedAssertion(final Fixture fixture, final Assertion assertion) {
    this.fixture = fixture;
    this.assertion = assertion;
  }

  @Override
  public void check() throws Exception {
    fixture.run();
    assertion.check();
  }
}
