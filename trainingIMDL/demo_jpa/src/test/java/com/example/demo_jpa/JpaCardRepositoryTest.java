package com.example.demo_jpa;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@SpringBootTest
@Testcontainers
public class JpaCardRepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName
            .parse("postgres:16-alpine"));

    @BeforeEach
    public void startContainer() {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> postgreSQLContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> postgreSQLContainer.getUsername());
        registry.add("spring.datasource.password", () -> postgreSQLContainer.getPassword());

        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.jpa.show-sql", () -> true);
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    JpaCardRepository cardRepo;

    @Test
    public void testForFind() {
        DbSetup dbSetupElement = new DbSetup(new DataSourceDestination(dataSource),
                Operations
                        .insertInto("element")
                        .row()
                        .column("id", 100)
                        .column("name", "Test Ele")
                        .end().build());

        DbSetup dbSetupCard = new DbSetup(new DataSourceDestination(dataSource),
                Operations
                        .insertInto("card")
                        .row()
                        .column("id", 1000)
                        .column("name", "Test Card")
                        .column("element_id", 100)
                        .column("top", 1)
                        .column("\"right\"", 2)
                        .column("bottom", 3)
                        .column("\"left\"", 4)
                        .end()
                        .build());

        dbSetupElement.launch();
        dbSetupCard.launch();

        Card actual = cardRepo.findById(1000).orElse(null);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getName()).isEqualTo("Test Card");
    }
}
