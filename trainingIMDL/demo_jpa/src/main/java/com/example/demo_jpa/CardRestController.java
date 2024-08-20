package com.example.demo_jpa;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardRestController {
    private final JpaCardRepository jpaCardRepository;

    public CardRestController(JpaCardRepository jpaCardRepository) {
        this.jpaCardRepository = jpaCardRepository;
    }

    @GetMapping
    public List<Card> getList(
            @RequestParam(value = "level", required = false)
            Integer level
    ) {
        if (level == null) {
            return jpaCardRepository.findAll();
        } else {
//            Card condition = new Card();
//            condition.setLevel(level);
//            return jpaCardRepository.findAll(
//                    Example.of(condition));
            return jpaCardRepository.findAllByLevel(level);
        }
    }

    @GetMapping("/{id}")
    public Card getById(
            @PathVariable("id") Integer id
    ) {
        return jpaCardRepository.findById(id)
                .orElse(null);
    }

    @PostMapping
    public Card post(
            @RequestBody Card card
    ) {
        return jpaCardRepository.save(card);
    }

    @PutMapping("/{id}")
    public Card put(
            @PathVariable("id") Integer id,
            @RequestBody Card card
    ) {
        return jpaCardRepository.findById(id).map(
                        c -> {
                            c.setName(card.getName());
                            c.setLevel(card.getLevel());
                            c.setElement(card.getElement());
                            c.setTop(card.getTop());
                            c.setRight(card.getRight());
                            c.setBottom(card.getBottom());
                            c.setLeft(card.getLeft());
                            return jpaCardRepository.save(c);
                        })
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "resource not found")
                );
    }
}
