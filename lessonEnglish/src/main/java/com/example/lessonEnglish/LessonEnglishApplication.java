package com.example.lessonEnglish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LessonEnglishApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessonEnglishApplication.class, args);
		System.out.println(init(5));
	}
	public static Integer init(int n) {
		if(n==1) {
			return 1;
		}else {
			return n * init(n-1);
		}
	}
}
