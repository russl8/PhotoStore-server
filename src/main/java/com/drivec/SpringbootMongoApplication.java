package com.drivec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class SpringbootMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoApplication.class, args);
	}


//	@Scheduled(cron = "0 */10 * * * ?")
	@Scheduled(cron = " */10 * * * * ?")
	public void keepServerRunning() {
		System.out.println("Cron: " + LocalDateTime.now());
	}

}
