package ro.ubb.bookstore.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ro.ubb.bookstore.client.ui.ClientConsole;

@Configuration
public class ClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ClientConsole clientConsole() {
        return new ClientConsole(restTemplate());
    }
}


