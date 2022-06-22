package se.etraveli.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import se.etraveli.Application;
import se.etraveli.dto.CustomerDto;
import se.etraveli.dto.MovieRental;

import java.util.Arrays;

@ActiveProfiles("test")
@SpringBootTest(classes = {Application.class})
@ContextConfiguration(initializers = {RetailInfoServiceTest.Initializer.class})
public class RetailInfoServiceTest {
    public static PostgreSQLContainer pgDb = new PostgreSQLContainer(DockerImageName.parse("postgres:12"));

    @Autowired
    private RetailInfoService retailInfoService;

    @BeforeAll
    public static void beforeClass() {
        pgDb.start();
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + pgDb.getJdbcUrl(),
                    "spring.datasource.username=" + pgDb.getUsername(),
                    "spring.datasource.password=" + pgDb.getPassword(),
                    "spring.datasource.driver-class-name=" + org.postgresql.Driver.class.getName()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void statementTest() {
        String statement = retailInfoService.statement(new CustomerDto("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
        Assertions.assertEquals(statement, "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n");
    }
}
