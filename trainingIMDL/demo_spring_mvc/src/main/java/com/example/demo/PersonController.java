package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @GetMapping("/{id:^[0-9]{4}$}")
    public Person get(
            @PathVariable String id,
            @RequestHeader(value = "X-Custom-Header", required = false) String xCustomHeader
    ) {
        System.out.println(xCustomHeader);

        return new Person(
                id, "alice", 7
        );
    }

    @PostMapping
    public Person post(@RequestBody Person person) {
        return person;
    }

    @PutMapping("/{id}")
    public Person put(
            @PathVariable("id") String id,
            @RequestBody Person person
    ) {
        if (!Objects.equals(person.getId(), id)) {
            throw new IllegalArgumentException("id mismatch");
        }

        return person;
    }

    @PatchMapping("/{id}")
    public Person patch(
            @PathVariable("id") String id,
            @RequestBody PersonPatchRequest personPatch
    ) {
        Person exitedPerson = new Person(
                "0001", "alice", 7);

        Person updatedPerson = exitedPerson.copy(
                personPatch.getId(),
                personPatch.getName(),
                personPatch.getAge()
        );

        return updatedPerson;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") String id
    ) {

    }

    @GetMapping("/error")
    public void getError() {
        throw new RuntimeException("Exception handle demo");
    }

    @GetMapping("/errorArgs")
    public void getErrorArgs() {
        throw new IllegalArgumentException("error args handle demo");
    }
}
