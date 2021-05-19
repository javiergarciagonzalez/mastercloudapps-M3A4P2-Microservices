package es.codeurjc.books;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import es.codeurjc.books.repositories.UserRepository;
import es.codeurjc.books.restclients.UserClient;
import es.codeurjc.books.restclients.UserClientMicroService;
import es.codeurjc.books.restclients.UserClientMicroServiceImpl;
import es.codeurjc.books.restclients.UserClientRepository;

@SpringBootApplication
@EnableFeignClients
public class Application {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper(Arrays.asList("dozer_mapping.xml"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConditionalOnProperty(value = "WITH_MICROSERVICES", havingValue = "true")
    public UserClient userClientService(UserClientMicroService userClientMicroService) {
        return new UserClientMicroServiceImpl(userClientMicroService);
    }

    @Bean
    @ConditionalOnProperty(value = "WITH_MICROSERVICES", havingValue = "false")
    public UserClient userClientMonolith(UserRepository userRepository) {
        return new UserClientRepository(userRepository);
    }

}
