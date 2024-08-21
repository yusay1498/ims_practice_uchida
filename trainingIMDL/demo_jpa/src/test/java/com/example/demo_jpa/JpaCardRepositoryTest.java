package com.example.demo_jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
@DataJpaTest
// Hint: @DataJpaTestでは、デフォルトでH2等の組み込みデータベースを使用するため、
//       TestContainers等を使用する場合はデフォルト挙動を抑制する
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class JpaCardRepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName
            .parse("postgres"));

    @BeforeAll
    static void startContainers() {
        postgresContainer.start();
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> postgresContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> postgresContainer.getUsername());
        registry.add("spring.datasource.password", () -> postgresContainer.getPassword());

        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.jpa.show-sql", () -> true);
    }

    @Autowired
    JpaCardRepository cardRepo;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void testForFind() {
        Element testElement = new Element();
        testElement.setId(100);
        testElement.setName("Test Ele");

        testEntityManager.persist(testElement);

        Card testCard = new Card();
        testCard.setId(1000);
        testCard.setName("Test Card");
        testCard.setLevel(1);
        testCard.setElement(testElement);
        testCard.setTop(1);
        testCard.setRight(2);
        testCard.setBottom(3);
        testCard.setLeft(4);

        testEntityManager.persist(testCard);

        Card actual = cardRepo.findById(1000).orElse(null);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getName()).isEqualTo("Test Card");
    }

    @Test
    public void testForSave() {
        //NOTE テストに必要な事前データ
        Element testElement = new Element();
        testElement.setId(200);
        testElement.setName("Test Save Ele");

        testEntityManager.persist(testElement);

        //NOTE テスト対象
        Card card = new Card();
        card.setId(2000);
        card.setName("Test Save Card");

        Element element = new Element();
        element.setId(200);
        element.setName("Test Save Ele");

        card.setElement(element);
        card.setTop(9);
        card.setRight(8);
        card.setBottom(7);
        card.setLeft(6);
        Card savedCard = cardRepo.save(card);
        //NOTE テスト対象（ここまで）

        //NOTE テスト対象の実行結果をアサーションする
        Assertions.assertThat(savedCard).isNotNull();
        Assertions.assertThat(savedCard.getId()).isEqualTo(2000);
        Assertions.assertThat(savedCard.getName()).isEqualTo("Test Save Card");

        Card actualCard = testEntityManager.find(Card.class, 2000);

        Assertions.assertThat(actualCard).isNotNull();
        Assertions.assertThat(actualCard.getId()).isEqualTo(2000);
        Assertions.assertThat(actualCard.getName()).isEqualTo("Test Save Card");
    }
}