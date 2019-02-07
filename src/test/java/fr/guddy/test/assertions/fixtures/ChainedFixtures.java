package fr.guddy.test.assertions.fixtures;

import java.util.List;
import org.assertj.core.util.Lists;

public final class ChainedFixtures implements Fixture {

  private final List<Fixture> fixtures;

  public ChainedFixtures(final List<Fixture> fixtures) {
    this.fixtures = fixtures;
  }

  public ChainedFixtures(final Fixture... fixtures) {
    this(Lists.newArrayList(fixtures));
  }

  @Override
  public void run() {
    fixtures.forEach(Fixture::run);
  }
}
