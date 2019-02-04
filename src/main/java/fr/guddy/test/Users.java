package fr.guddy.test;

import io.javalin.ConflictResponse;
import io.javalin.Context;
import io.javalin.ForbiddenResponse;
import io.javalin.NotFoundResponse;
import io.javalin.apibuilder.CrudHandler;
import org.dizitart.no2.objects.ObjectRepository;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public final class Users implements CrudHandler {

    private final ObjectRepository<User> repository;

    public Users(final ObjectRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public void getAll(@NotNull final Context context) {
        final List<User> users = repository.find().toList();
        if (users.isEmpty()) {
            context.status(HttpStatus.NO_CONTENT_204);
        } else {
            context.status(HttpStatus.OK_200).json(users);
        }
    }

    @Override
    public void getOne(
        @NotNull final Context context,
        @NotNull final String userId
    ) {
        final List<User> users = repository.find(eq("userId", userId)).toList();
        if (users.isEmpty()) {
            throw new NotFoundResponse(
                    String.format("User with id \"%s\" not found", userId)
            );
        }
        context.status(HttpStatus.OK_200).json(users.get(0));
    }

    @Override
    public void create(@NotNull final Context context) {
        final User user = context.bodyAsClass(User.class);
        if (!repository.find(eq("userId", user.getUserId()))
            .toList()
            .isEmpty()
        ) {
            throw new ConflictResponse(
                    String.format(
                        "User with id \"%s\" already exists",
                        user.getUserId()
                    )
            );
        }
        context.status(HttpStatus.CREATED_201).json(user);
    }

    @Override
    public void update(
        @NotNull final Context context,
        @NotNull final String userId
    ) {
        throw new ForbiddenResponse("not implemented");
    }

    @Override
    public void delete(
        @NotNull final Context context,
        @NotNull final String userId
    ) {
        final List<User> users = repository.find(eq("userId", userId)).toList();
        if (users.isEmpty()) {
            throw new NotFoundResponse(
                    String.format("User with id \"%s\" not found", userId)
            );
        }
        repository.remove(users.get(0));
        context.status(HttpStatus.OK_200);
    }
}
