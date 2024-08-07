package dev.dubrovsky.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "dev.dubrovsky")
@EnableJpaRepositories(basePackages = "dev.dubrovsky.repository")
@EntityScan(basePackages = "dev.dubrovsky.model")
@EnableScheduling
public class TgbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgbotApplication.class, args);
    }

}
