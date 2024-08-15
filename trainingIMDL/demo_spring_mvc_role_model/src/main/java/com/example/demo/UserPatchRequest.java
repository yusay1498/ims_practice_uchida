package com.example.demo;

import java.util.Optional;

public class UserPatchRequest {
    Optional<String> id;
    Optional<String> email;
    Optional<String> name;

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public User merge(User origin) {
        String id = origin.getId();
        String mergedEmail = email != null
                ? email.orElse(null)
                : origin.getEmail();
       String mergedName = email != null
               ? name.orElse(null)
               : origin.getName();

        return new User(
                id,
                mergedEmail,
                mergedName
        );
    }
}
