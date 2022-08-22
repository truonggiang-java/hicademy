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
		System.out.println(init(5));
		String number = "7";
	     String abcde="0" + "7";
      
        System.out.println(abcde);

	}
	public static Integer init(int n) {
		if(n==1) {
			return 1;
		}else {
			return n * init(n-1);
		}
	}
}
