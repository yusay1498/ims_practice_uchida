package com.example.demo_jdbc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
public class JdbcCardRepositoryTests {
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName
            .parse("postgres"));

    @BeforeAll
    static void startContainers() {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

        //NOTE JDBCの場合は、schema.sql を実行するために、この設定を追加する
        registry.add("spring.sql.init.mode", () -> "always");
    }

    @Autowired
    JdbcClient jdbcClient;

    @Sql(statements = {
            """
            INSERT INTO card(id, name, level, element_id, top, "right", bottom, "left") \
            VALUES(1000, 'Test Card', 1 , 100, 1, 2, 3, 4)
            """
    })
    @Test
    void testFindById() {
        JdbcCardRepository cardRepo = new JdbcCardRepository(jdbcClient);

        Card card = cardRepo.findById(1000);

        Assertions.assertThat(card).isNotNull();
        Assertions.assertThat(card.id()).isEqualTo(1000);
        Assertions.assertThat(card.name()).isEqualTo("Test Card");
    }

    @Test
    void testSave() {
        JdbcCardRepository cardRepo = new JdbcCardRepository(jdbcClient);

        Card card = new Card(2000, "Save Card", 2, 20, 9 ,8, 7, 6);

        Card savedCard = cardRepo.save(card);

        //NOTE save メソッドの戻り値を確認
        Assertions.assertThat(savedCard).isNotNull();
        Assertions.assertThat(savedCard.id()).isEqualTo(2000);
        Assertions.assertThat(savedCard.name()).isEqualTo("Save Card");

        //NOTE DB内のレコードに挿入したデータが存在することを確認
        jdbcClient
                .sql(
                """
                    SELECT 
                        id, name, level, element_id, top, "right", bottom, "left"
                    FROM 
                        card
                    WHERE 
                        id = :id
                """)
                .param("id", 2000)
                .query(DataClassRowMapper.newInstance(Card.class))
                .single();

        Assertions.assertThat(savedCard).isNotNull();
        Assertions.assertThat(savedCard.id()).isEqualTo(2000);
        Assertions.assertThat(savedCard.name()).isEqualTo("Save Card");
    }
}
