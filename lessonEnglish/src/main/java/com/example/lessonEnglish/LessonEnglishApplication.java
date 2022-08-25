package com.example.lessonEnglish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LessonEnglishApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessonEnglishApplication.class, args);
		String[] abc= new String[5];
		String bce=abc.toString();
		if(bce !=null) {
			System.out.println("bcde");
		}else {
			System.out.println("ac");
		}
	}
	
}
