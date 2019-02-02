package fr.guddy.test;

import org.dizitart.no2.Nitrite;

public final class Main {
    public static void main(final String[] args) {
        final Nitrite database = Nitrite.builder()
                .compressed()
                .openOrCreate("user", "password");
        final Application app = new UserApi(7000);
        app.start();
    }
}
