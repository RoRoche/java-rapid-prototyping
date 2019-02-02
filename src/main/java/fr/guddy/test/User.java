package fr.guddy.test;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.Property;

import java.io.Serializable;

@Indices({
        @Index(value = "userId", type = IndexType.Unique)
})
public final class User implements Serializable {
    @Id
    @Property
    private String userId;
    @Property
    private String firstName;
    @Property
    private String lastName;

    public User() {
    }

    public User(final String userId, final String firstName, final String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(final Object obj) {
        return Pojomatic.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode(this);
    }
}
