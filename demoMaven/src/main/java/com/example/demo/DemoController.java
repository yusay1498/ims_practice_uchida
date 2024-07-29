package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    @GetMapping
    public Map<String, Object> get() {
        return Map.ofEntries(
                Map.entry("name", "Alice"),
                Map.entry("age", 7)
        );
    }

    @GetMapping("withParam")
    public Map<String, Object> getWithParam(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        return Map.ofEntries(
                Map.entry("name", name),
                Map.entry("age", age)
        );
    }

    @GetMapping("withPath/{name}/{age}")
    public Map<String, Object> getWithPath(
            @PathVariable String name,
            @PathVariable int age
    ) {
        return Map.ofEntries(
                Map.entry("name", name),
                Map.entry("age", age)
        );
    }

    @PostMapping
    public Map<String, Object> post() {
        return Map.ofEntries(
                Map.entry("name", "Alice"),
                Map.entry("age", 7)
        );
    }

    @PostMapping("/withBody")
    public Map<String, Object> postWithBody(
            @RequestBody Map<String, Object> body
    ) {
        return Map.ofEntries(
                Map.entry("name", body.get("name")),
                Map.entry("age", body.get("age"))
        );
    }

    @PutMapping("/{name}")
    public Map<String, Object> put(
            @RequestBody Map<String, Object> body,@PathVariable String name
    ) {
        return Map.ofEntries(
                Map.entry("name", body.get("name")),
                Map.entry("age", body.get("age"))
        );
    }

    @DeleteMapping("/{name}")
    public void delete(
            @PathVariable String name
    ) {

    };

}
