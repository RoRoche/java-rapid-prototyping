package fr.guddy.test;

import org.dizitart.no2.Nitrite;

public final class Main {

  public static void main(final String[] args) {
    final Nitrite database = Nitrite.builder()
        .openOrCreate("user", "password");
    final Application app = new UserApi(7000, database);
    app.start();
  }
}
