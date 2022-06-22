//package se.etraveli.service.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import se.etraveli.service.RetailInfoServiceTest;
//
//import java.util.Properties;
//
//@Configuration
//public class ApplicationTestContext {
//    @Bean
//    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        var propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//        var properties = new Properties();
//        properties.setProperty("spring.datasource.url", RetailInfoServiceTest.pgDb.getJdbcUrl());
//        properties.setProperty("spring.datasource.username", RetailInfoServiceTest.pgDb.getUsername());
//        properties.setProperty("spring.datasource.password", RetailInfoServiceTest.pgDb.getPassword());
//        properties.setProperty("spring.datasource.driver-class-name", org.postgresql.Driver.class.getName());
//        propertySourcesPlaceholderConfigurer.setProperties(properties);
//        return propertySourcesPlaceholderConfigurer;
//    }
//}
