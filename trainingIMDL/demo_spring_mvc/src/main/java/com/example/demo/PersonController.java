package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @GetMapping
    public Person get(
            @RequestParam("age") int age
    ) {
        return new Person(
                "0001", "alice", age
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
        if (!Objects.equals(person.id(), id)) {
            throw new IllegalArgumentException("id mismatch");
        }

        return person;
    }
}
