package com.example.demo_jdbc;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCardRepository {
    private final JdbcClient jdbcClient;

    public JdbcCardRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Card> findHighPoints(int level) {
        return jdbcClient
                .sql("""
                    WITH
                      card_sum AS (
                        SELECT
                          card.id, card.name, card.level, card.element_id,
                          card.top, card.right, card.bottom, card.left,
                          (card.top + card.right + card.bottom + card.left) AS summary
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
                """)
                .param("level", level)
                .query(DataClassRowMapper.newInstance(Card.class))
                .list();
    }

    public List<Card> findHighPoints_ResultSet(int level) {
        return jdbcClient
                .sql("""
                    WITH
                      card_sum AS (
                        SELECT
                          card.id, card.name, card.level, card.element_id,
                          card.top, card.right, card.bottom, card.left,
                          (card.top + card.right + card.bottom + card.left) AS summary
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
                """)
                .param("level", level)
                .query((resultSet, rowNum) -> {
                    resultSet.next();
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer levelResult = resultSet.getInt("level");
                    Integer elementId = resultSet.getInt("element_id");
                    Integer top = resultSet.getInt("top");
                    Integer right = resultSet.getInt("right");
                    Integer bottom = resultSet.getInt("bottom");
                    Integer left = resultSet.getInt("left");

                    return new Card(id, name, levelResult, elementId, top, right, bottom, left);
                })
                .list();
    }

    public Card findById(int id) {
        return jdbcClient
                .sql("""
                    SELECT
                        id,
                        name,
                        level,
                        element_id,
                        top,
                        "right",
                        bottom,
                        "left"
                    FROM
                        card
                    WHERE
                        id = :id
                """)
                .param("id", id)
                .query(DataClassRowMapper.newInstance(Card.class))
                .single();
    }

    public Card save(Card card) {
        jdbcClient
                .sql("""
                    INSERT INTO card(id, name, level, element_id, top, "right", bottom, "left") 
                    VALUES(:id, :name, :level, :elementId, :top, :right, :bottom, :left)
                """)
                .param("id", card.id())
                .param("name", card.name())
                .param("level", card.level())
                .param("elementId", card.elementId())
                .param("top", card.top())
                .param("right", card.right())
                .param("bottom", card.bottom())
                .param("left", card.left())
                .update();

        return findById(card.id());
    }
}
