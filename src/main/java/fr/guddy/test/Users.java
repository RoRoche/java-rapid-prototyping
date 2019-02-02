package fr.guddy.test;

import io.javalin.Context;
import io.javalin.ForbiddenResponse;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;

public final class Users implements CrudHandler {
    @Override
    public void getAll(@NotNull final Context context) {
        throw new ForbiddenResponse("not implemented");
    }

    @Override
    public void getOne(@NotNull final Context context, @NotNull final String userId) {
        throw new ForbiddenResponse("not implemented");
    }

    @Override
    public void create(@NotNull final Context context) {
        throw new ForbiddenResponse("not implemented");
    }

    @Override
    public void update(@NotNull final Context context, @NotNull final String userId) {
        throw new ForbiddenResponse("not implemented");
    }

    @Override
    public void delete(@NotNull final Context context, @NotNull final String userId) {
        throw new ForbiddenResponse("not implemented");
    }
}
