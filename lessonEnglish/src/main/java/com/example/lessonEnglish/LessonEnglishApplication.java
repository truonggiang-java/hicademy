package com.example.lessonEnglish;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LessonEnglishApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessonEnglishApplication.class, args);
	}
}
