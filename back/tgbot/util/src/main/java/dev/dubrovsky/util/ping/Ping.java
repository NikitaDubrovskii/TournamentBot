package dev.dubrovsky.util.ping;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Ping {

    private final RestTemplate restTemplate;

    public Ping(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 300_000)
    private void keepBotAlive() {
        restTemplate.getForObject("https://tournamentbotbackend.onrender.com", String.class);
    }

}
