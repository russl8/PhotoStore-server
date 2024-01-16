package com.drivec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class SpringbootMongoApplication {

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoApplication.class, args);
	}

	@Scheduled(cron = "0 */10 * * * ?")
	public void keepServerRunning() {
		System.out.println("Cron: " + LocalDateTime.now());

		// Make GET request to the same server
		String url = "http://localhost:8080/your-endpoint"; // Replace with your actual endpoint
		String response = restTemplate.getForObject(url, String.class);

		// Process the response as needed
		System.out.println("Response: " + response);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
