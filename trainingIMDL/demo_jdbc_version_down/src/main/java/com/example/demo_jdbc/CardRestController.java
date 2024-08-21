package com.example.demo_jdbc;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardRestController {
    private NamedParameterJdbcOperations jdbcOps;

    public CardRestController(NamedParameterJdbcOperations jdbcOps) {
        this.jdbcOps = jdbcOps;
    }

    @GetMapping("/highPoints/{level}")
    public List<Card> getByHighPoint(@PathVariable int level) {
        List<Card> cards = jdbcOps.query("""
                WITH
                  card_sum AS (
                    SELECT
                      card.id, card.name, card.level, card.element_id, (card.top + card.right + card.bottom + card.left) AS summary
                    FROM card
                  ),
                  card_sum_max AS (
                    SELECT
                      card.level, MAX(card.top + card.left + card.bottom + card.right) AS summary_max
                    FROM card
                    GROUP BY card.level
                  )
                                
                SELECT * FROM card_sum INNER JOIN card_sum_max
                ON  card_sum.level = card_sum_max.level
                AND card_sum.summary = card_sum_max.summary_max
                                
                WHERE card_sum.level = :level
                
                ORDER BY card_sum.level, card_sum.id;
                """,
                new MapSqlParameterSource()
                        .addValue("level", level),
                DataClassRowMapper.newInstance(Card.class));

        return cards;
    }

//    @GetMapping("/highPoints2")
//    public List<Card> getByHighPoint2() {
//        List<Card> cards = jdbcOps.query("""
//                WITH
//                  card_sum AS (
//                    SELECT
//                      card.id, card.name, card.level, card.element_id, (card.top + card.right + card.bottom + card.left) AS summary
//                    FROM card
//                  ),
//                  card_sum_max AS (
//                    SELECT
//                      card.level, MAX(card.top + card.left + card.bottom + card.right) AS summary_max
//                    FROM card
//                    GROUP BY card.level
//                  )
//
//                SELECT * FROM card_sum INNER JOIN card_sum_max
//                ON  card_sum.level = card_sum_max.level
//                AND card_sum.summary = card_sum_max.summary_max
//
//                ORDER BY card_sum.level, card_sum.id;
//                """, (resultSet, roeNum) -> {
//            resultSet.next();
//            Integer id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            Integer level = resultSet.getInt("level");
//            Integer elementId = resultSet.getInt("element_Id");
//            Integer summary = resultSet.getInt("summary");
//
//            return new Card(id, name, level, elementId, summary);
//        });
//
//        return cards;
//
//    }


    @GetMapping("/highPoints2/{level}")
    public List<Card> getByHighPoint2(
            @PathVariable("level") int levelParam
    ) {
        List<Card> cards = jdbcOps.query("""
                WITH
                  card_sum AS (
                    SELECT
                      card.id, card.name, card.level, card.element_id, (card.top + card.right + card.bottom + card.left) AS summary
                    FROM card
                  ),
                  card_sum_max AS (
                    SELECT
                      card.level, MAX(card.top + card.left + card.bottom + card.right) AS summary_max
                    FROM card
                    GROUP BY card.level
                  )
                                
                SELECT * FROM card_sum INNER JOIN card_sum_max
                ON  card_sum.level = card_sum_max.level
                AND card_sum.summary = card_sum_max.summary_max
                                       
                         
                WHERE card_sum.level = :level
                  
                ORDER BY card_sum.level, card_sum.id;
                """,
                new MapSqlParameterSource()
                        .addValue("level", levelParam),
                (resultSet, roeNum) -> {
                    resultSet.next();
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer level = resultSet.getInt("level");
                    Integer elementId = resultSet.getInt("element_Id");
                    Integer summary = resultSet.getInt("summary");

                    return new Card(id, name, level, elementId, summary);
                });

        return cards;

    }
}
