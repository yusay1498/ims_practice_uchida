package com.example.demo_jdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardRestController {
    private final JdbcCardRepository cardRepository;

    public CardRestController(JdbcCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/highPoints/{level}")
    public List<Card> getByHighPoints(
            @PathVariable("level") int level
    ) {
        return cardRepository.findHighPoints(level);
    }


    @GetMapping("/highPoints2/{level}")
    public List<Card> getByHighPoints2(
            @PathVariable("level") int level
    ) {
        return cardRepository.findHighPoints_ResultSet(level);
    }
}
