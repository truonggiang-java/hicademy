package com.example.lessonEnglish;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LessonEnglishApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessonEnglishApplication.class, args);
		LocalDate date=LocalDate.now();
		date.plusDays(1);
		LocalDate date1=LocalDate.now();
		System.out.println(date.isBefore(date1));
	}
}
